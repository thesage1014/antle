package ants.ml;

import java.io.IOException;

import org.deeplearning4j.api.storage.StatsStorage;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.optimize.api.TrainingListener;
import org.deeplearning4j.rl4j.learning.ILearning;
import org.deeplearning4j.rl4j.learning.sync.qlearning.QLearning;
import org.deeplearning4j.rl4j.learning.sync.qlearning.discrete.QLearningDiscreteDense;
import org.deeplearning4j.rl4j.network.dqn.DQNFactoryStdDense;
import org.deeplearning4j.rl4j.space.DiscreteSpace;
import org.deeplearning4j.rl4j.util.DataManager;
import org.deeplearning4j.ui.api.UIServer;
import org.deeplearning4j.ui.stats.StatsListener;
import org.deeplearning4j.ui.storage.InMemoryStatsStorage;
import org.nd4j.linalg.learning.config.Adam;

import ants.TickThread;

public class AntLearningManager {
	public static QLearning.QLConfiguration ANT_CONFIG = new QLearning.QLConfiguration(123, // Random seed
			100000, // Max step By epoch
			80000, // Max step
			10000, // Max size of experience replay
			400, // size of batches
			100, // target update (hard)
			0, // num step noop warmup
			0.05, // reward scaling
			0.99, // gamma
			10.0, // td-error clipping
			0.1f, // min epsilon
			2000, // num step for eps greedy anneal
			true // double DQN
	);
	
	public void testAnt() throws IOException {
		// record the training data
		DataManager manager = new DataManager();
		
		AntMDP mdp = new AntMDP();

		UIServer uiServer = UIServer.getInstance();

		// Configure where the network information (gradients, activations, score vs. time etc) is to be stored. Then add the StatsListener to collect this information from the network, as  it trains
		
		StatsStorage statsStorage = new InMemoryStatsStorage(); // Alternative: new FileStatsStorage(File) - see UIStorageExample
		TrainingListener[] listeners = new StatsListener[] { new StatsListener(statsStorage) };
		
		DQNFactoryStdDense.Configuration antNet = DQNFactoryStdDense.Configuration.builder().listeners(listeners)
				.l2(0.01).updater(new Adam(1e-2)).numLayer(3).numHiddenNodes(16).build();
		
		ILearning<AntStateData, Integer, DiscreteSpace> dql = new QLearningDiscreteDense<AntStateData>(mdp, antNet, ANT_CONFIG, manager);

		uiServer.attach(statsStorage);
		 
		dql.train();

		// useless on ant but good practice!
		mdp.close();

	}
	public void attachNewBrain(TickThread intickThread) {
		try {
			DataManager manager = new DataManager();
			
			AntMDP mdp = new AntMDP(intickThread);
			
			UIServer uiServer = UIServer.getInstance();
			
			// Configure where the network information (gradients, activations, score vs. time etc) is to be stored. Then add the StatsListener to collect this information from the network, as  it trains
			StatsStorage statsStorage = new InMemoryStatsStorage(); // Alternative: new FileStatsStorage(File) - see UIStorageExample
			
			TrainingListener[] listeners = new StatsListener[] { new StatsListener(statsStorage) };
			
			DQNFactoryStdDense.Configuration antNet = DQNFactoryStdDense.Configuration.builder().listeners(listeners)
					.l2(0.01).updater(new Adam(1e-2)).numLayer(3).numHiddenNodes(16).build();
			
			ILearning<AntStateData, Integer, DiscreteSpace> dql = new QLearningDiscreteDense<AntStateData>(mdp, antNet, ANT_CONFIG, manager);
			
			uiServer.attach(statsStorage);
			
			dql.train();
			
			// useless on ant but good practice!
			mdp.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws IOException {
		AntLearningManager testAnt = new AntLearningManager();
		testAnt.testAnt();
	}
}
