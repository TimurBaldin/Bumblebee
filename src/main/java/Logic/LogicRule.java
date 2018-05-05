package Logic;
import TipDataRule.*;
import Tools.SessionUntil;

public class LogicRule {
    public static void main(String[] args) {
      LogicRule l=new LogicRule ();
        l.interaction ();
      }

        public void interaction() {

        /*
        Пример входных пользовательских данных
         */
       RuleString RS = new RuleString(80, 30, false, true, true, true, "Test");
        RuleString RT = new RuleString(80, 30, false, true, true, true, "New val");
        try {
            RS.buildTestData();
           RT.buildTestData();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            SessionUntil.INSTANCE.close ();
        }
        }
}
