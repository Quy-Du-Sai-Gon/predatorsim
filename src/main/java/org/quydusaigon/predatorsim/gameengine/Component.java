package org.quydusaigon.predatorsim.gameengine;

public abstract class Component {

    private GameObject gameObject;

    public void Awake(){

    }

    public void Start(){

    }

    public void Update(){

    }

    public void onAdded(){

    }

    public void onRemove() {

    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public GameObject setGameObject(GameObject gameObject){
        this.gameObject = gameObject;
        return gameObject;
    }
}
