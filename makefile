JCC = javac

default: numpartition.class binarySolution.class localSearch.class PrepartitionSolution.class

numpartition.class: numpartition.java
	$(JCC) numpartition.java
	
binarySolution.class: binarySolution.java solution.java
	$(JCC) binarySolution.java solution.java

PrepartitionSolution.class: PrepartitionSolution.java solution.java
	$(JCC) PrepartitionSolution.java solution.java
	
localSearch.class: localSearch.java
	$(JCC) localSearch.java

clean:
	$(RM) *.class