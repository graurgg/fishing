# Fishing

---
### Current features

 - The *help* command for displaying all available commands
 - A primitive json database held in the \data\ folder, with support for adding fish and rods (via the commands *addfish* and *addrod*)
 - A decoder class for "translating" keyboard inputs (along with some typos!) into commands

---
### How to run

 - Run the batch file present in [/install/fishing-game/bin](build/install/fishing-game/bin)
 - Probably needs [Java 20](https://www.oracle.com/java/technologies/javase/jdk20-archive-downloads.html)

---
### Roadmap

(no particular order)
#### Short-term

1. Populate database with items
2. Test the *fish* command
3. Change the *goto* command to take an argument instead of prompting the user
4. Change the math formula for fishing rod power
5. Add a player-inventory for owned rods (and also a method to buy a rod)
#### Long-term
1. Add a player object for stuff like equipment and inventory
2. Add a travelling system for fishing in other places with different fish pools
3. Add a skill-check to the *fish* command
4. Add an "admin" mode for running debugging commands
5. Add a json for storing user data (saving progress)
