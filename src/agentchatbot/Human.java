package agentchatbot;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class Human extends Agent {

    protected void setup() {
        // addBehaviour(new agentbehaviour());
        ACLMessage mes = new ACLMessage(ACLMessage.INFORM);
        mes.addReceiver(new AID("ChatBot", AID.ISLOCALNAME));
        mes.setContent(AgentChatBot.text.getText());
        send(mes);
    }
}
