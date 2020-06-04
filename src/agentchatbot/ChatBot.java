package agentchatbot;

import jade.core.Agent;

public class ChatBot extends Agent {

    protected void setup() {
        this.addBehaviour(new RecieveBehavior());
    }
}
