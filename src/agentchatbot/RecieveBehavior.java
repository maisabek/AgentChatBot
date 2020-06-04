package agentchatbot;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.StringTokenizer;

public class RecieveBehavior extends CyclicBehaviour {

    String s = new String("s");
    String str = new String("str");

    public void action() {
        try {
            ACLMessage aa = this.getAgent().receive();
            if (aa != null) {
                str = new String(aa.getContent()); // gui input
            } else {
                block();
            }
            //processSMS(str);
        } catch (Exception e) {
            System.out.println("RB Error " + e.toString());
        }
    }

    String processSMS(String SMS) {
        String token;
        StringTokenizer Token = new StringTokenizer(SMS);
        while (Token.hasMoreTokens()) {
            token = Token.nextToken().toLowerCase();
            if (token.equals("location") || token.equals("where") || token.equals("place")) {
                new AgentChatBot().textarea1.appendText("\ttanta\n");
                break;
            }else if (token.equals("time") || token.equals("when")) {
                new AgentChatBot().textarea1.appendText("\t10 am\n");
                break;
            }else if (token.equals("hi") || token.equals("hellow")) {
                new AgentChatBot().textarea1.appendText("\tHi\n");
                break;
            }else if (token.equals("thank") || token.equals("thanks")) {
                new AgentChatBot().textarea1.appendText("\tyou are welcome\n");
                break;
            }else if (token.equals("work")) {
                new AgentChatBot().textarea1.appendText("\tWe are clothing shop\n");
                break;
            }
        }
        return s;
    }
}
