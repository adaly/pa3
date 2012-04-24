JCC = javac

default: numpartition.class binarySolution.class

numpartition.class: numpartition.java
	$(JCC) numpartition.java
	
binarySolution.class: binarySolution.java solution.java
	$(JCC) binarySolution.java solution.java

clean:
	$(RM) *.class