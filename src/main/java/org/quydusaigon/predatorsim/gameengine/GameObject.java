package org.quydusaigon.predatorsim.gameengine;

import java.util.List;

public class GameObject {
    public List<Component> components;

    public <T extends Component> T GetComponent(Class<T> componentClass){
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

    public <T extends Component> T AddComponent(Component c){
        components.add(c);
        c.setGameObject(this);
        return (T) c;
    }

    public <T extends Component> void RemoveComponent(Class<T> componentClass){
        for(int i = 0; i < components.size(); i++){
            if(componentClass.isAssignableFrom(components.get(i).getClass())){
                components.remove(i);
                return;
            }
        }
    }


}
