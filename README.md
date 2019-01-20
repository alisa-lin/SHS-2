# SHS 2
SHS 2 is a Choose Your Own Adventure game, played in the command line. You play a teenage boy in his senior year at a new high school as he navigates the world of classes, extracurriculars, friends, enemies, and more. 

## How to play
Download all files. Compile and run GameRunner.java. The narrative will begin in text form in your command line environment or IDE, and you will be able to control the story by making choices as they appear. 

## Notes
* The story is incomplete as only the introduction and the better part of the first week (of twelve weeks of story) have been written. 

* One ability that I'd like to add is multiple checkpoints within each week. Currently, information only saves at the end of each week because that allows the save file to be consistent at each save (i.e. the information that needs to be saved at each save point is the same). However, each week's storyline can be quite long, so it would be helpful to introduce stages of each week and save information at each stage.

* For those interested in taking this code and writing your own story, the instructions.txt file outlines the format of the story text that allows the story to be run. For examples of what it looks like, users may look at w1.txt. To run the newly added section of the story, you must write a method within Game.java that calls the readStory() method, passing in a BufferedReader object that reads in your text file.

* This project is named SHS 2 because it was partially borne out of my experience playing Surviving High School by EA Games as a child. Surviving High School has since been discontinued and is unavailable in any app store. SHS 2 is reminiscent of it in some senses but by no means a copy of it.

* An idea that would be cool to implement once this project is complete is to add graphics. However, besides the pain that Java graphics can be, it would completely change much of the code, mostly because every print statement would have to be rewritten (and as this is a game played on your command line, it is made up entirely of print statements). That said, this type of project would lend itself well to simple graphics.

* Created to learn more about multi-class Java projects and input/output to text files and from the command line.