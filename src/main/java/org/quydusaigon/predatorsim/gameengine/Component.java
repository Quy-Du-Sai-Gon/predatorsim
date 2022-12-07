package org.quydusaigon.predatorsim.gameengine;

public abstract class Component {

    private GameObject gameObject;

    public void Awake(){

    }

    public void start(){

    }

    public void update(){

    }

    public void onAdded(){

    }

    public void onRemoved() {

    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public GameObject setGameObject(GameObject gameObject){
        this.gameObject = gameObject;
        return gameObject;
    }
}
