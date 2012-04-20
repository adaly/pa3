JCC = javac

default: numpartition.class

numpartition.class: numpartition.java
	$(JCC) numpartition.java

clean:
	$(RM) *.class