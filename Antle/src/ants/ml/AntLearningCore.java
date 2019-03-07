package ants.ml;

import java.io.IOException;

import org.deeplearning4j.api.storage.StatsStorage;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.optimize.api.TrainingListener;
import org.deeplearning4j.rl4j.learning.ILearning;
import org.deeplearning4j.rl4j.learning.sync.qlearning.QLearning;
import org.deeplearning4j.rl4j.learning.sync.qlearning.discrete.QLearningDiscreteDense;
import org.deeplearning4j.rl4j.network.dqn.DQNFactoryStdDense;
import org.deeplearning4j.rl4j.policy.DQNPolicy;
import org.deeplearning4j.rl4j.policy.Policy;
import org.deeplearning4j.rl4j.space.Box;
import org.deeplearning4j.rl4j.space.DiscreteSpace;
import org.deeplearning4j.rl4j.util.DataManager;
import org.deeplearning4j.ui.api.UIServer;
import org.deeplearning4j.ui.stats.StatsListener;
import org.deeplearning4j.ui.storage.InMemoryStatsStorage;
import org.nd4j.linalg.learning.config.Adam;

import ants.MLInterface;
import ants.TickThread;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AntLearningCore implements Runnable{
	public MLInterface mlInterface;
	public final int RANDOM_SEED = 123;
	public final QLearning.QLConfiguration ANT_CONFIG = new QLearning.QLConfiguration(RANDOM_SEED, // Random seed
			10000,    //Max step By epoch
            8000000,  //Max step
            1000000,  //Max size of experience replay
            32,       //size of batches
            10000,    //target update (hard)
            500,      //num step noop warmup
            0.1,      //reward scaling
            0.99,     //gamma
            100.0,    //td-error clipping
            0.1f,     //min epsilon
            100000,   //num step for eps greedy anneal
            true      //double-dqn
	);
	public void attachNewBrain(MLInterface mli) {
		mlInterface = mli;
		new Thread(this).start();
	}
	@Override
	public void run() {
		try {
			DataManager manager = new DataManager();
			
			AntMDP mdp = new AntMDP(mlInterface);

			// Web UI stuff
			UIServer uiServer = UIServer.getInstance();
			StatsStorage statsStorage = new InMemoryStatsStorage(); // Alternative: new FileStatsStorage(File) - see UIStorageExample
			TrainingListener[] listeners = new StatsListener[] { new StatsListener(statsStorage) };
			
			// Configure where the network information (gradients, activations, score vs. time etc) is to be stored. Then add the StatsListener to collect this information from the network, as  it trains

			DQNFactoryStdDense.Configuration antNet = DQNFactoryStdDense.Configuration.builder().listeners(listeners)
					.l2(0.01).updater(new Adam(1e-2)).numLayer(3).numHiddenNodes(16).build();
			
			QLearningDiscreteDense<AntStateData> dql = new QLearningDiscreteDense<AntStateData>(mdp, antNet, ANT_CONFIG, manager);
			try {
				DQNPolicy<AntStateData> loadedPol = DQNPolicy.load("test.pol");
				dql.setTargetDQN(loadedPol.getNeuralNet());
				log.info("Loaded found policy with " + loadedPol.getNeuralNet().getLatestScore() + " latest score");
			} catch(IOException e) {
				log.info("Unable to load policy. Working from scratch");
			}
			
			uiServer.attach(statsStorage);
			
			dql.train();
	        //serialize and save (serialization showcase, but not required)
			DQNPolicy<AntStateData> pol = dql.getPolicy();
	        pol.save("test.pol");
	        log.info("Saved");
			mdp.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}