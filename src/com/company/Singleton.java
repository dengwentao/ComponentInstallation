package com.company;

/**
 * Created by dengw on 2/17/2017.
 */
public enum Singleton {
    INSTANCE;
    private final Graph graph;
    private Singleton() {
        graph = new Graph();
	}

	public Graph getInstance() {
	    return graph;
	}
}
