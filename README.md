# Antle - Ant sim machine learning environment
2D ant colony simulator in Java

The goal is to create an easy to use platform for people to design their own AI. The environment is easy to compute, yet could provide potentially limitless complexity as adversarial colonies compete with eachother. The game is planned to be multiplayer, with both players choosing an AI and configuring it on the fly, or battling against other players in the cloud.

The original goal was to learn more about collective thinking and see the more simple patterns, so this understanding can be applied to learn about humans interacting over the internet. However, with the rise of machine learning affectung our daily lives, it's become important to have this technology more accessible to the layperson.

![Early screenshot of the merged scent map](/Antle/screenshots/13.png?raw=true)

# Current State
So far, the game has a minimal backend for computing the environment including multiple layers of scent. There is food in the map which the ants can collect, but there is no goal beyond that implemented. A basic QLearning network is being implemented. Observations and actions are being passed through the rl4j library.

# Installation
This project uses Maven, and Oracle's JDK 1.8, which you'll need to install. If you're using Eclipse, you'll neet to install lombok.
