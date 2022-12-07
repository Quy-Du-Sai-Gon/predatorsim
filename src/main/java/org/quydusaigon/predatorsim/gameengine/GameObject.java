package org.quydusaigon.predatorsim.gameengine;

import java.util.ArrayList;
import java.util.List;

public class GameObject {

    public GameObject(Component ... a){
        components = new ArrayList<>();
        for(Component c : a){
            addComponent(c);
        }
    }
    protected List<Component> components;

    public <T extends Component> T getComponent(Class<T> componentClass){
        for(Component c : components){
            if(componentClass.isAssignableFrom(c.getClass())){
                try{
                    return componentClass.cast(c);
                }
                catch(ClassCastException e){
                    e.printStackTrace();
                    assert false : "GameObject does not have this component";
                }
            }
        }

        return null;
    }

    public <T extends Component> T addComponent(Component c){
        components.add(c);
        c.setGameObject(this);
        c.onAdded();
        return (T) c;
    }

    public <T extends Component> void removeComponent(Class<T> componentClass){
        for(int i = 0; i < components.size(); i++){
            if(componentClass.isAssignableFrom(components.get(i).getClass())){
                components.get(i).onRemoved();
                components.remove(i);
                return;
            }
        }
    }

    public void awake(){
        for(Component c : components){
            c.Awake();
        }
    }

    public void start(){
        for(Component c : components){
            c.start();
        }
    }

    public void update(){
        for(Component c : components){
            c.update();
        }
    }
}
