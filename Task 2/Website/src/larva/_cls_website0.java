package larva;


import java.util.LinkedHashMap;
import java.io.PrintWriter;

public class _cls_website0 implements _callable{

public static PrintWriter pw; 
public static _cls_website0 root;

public static LinkedHashMap<_cls_website0,_cls_website0> _cls_website0_instances = new LinkedHashMap<_cls_website0,_cls_website0>();
static{
try{
RunningClock.start();
pw = new PrintWriter("C:\\Users\\Luke\\workspace\\Website/src/output_website.txt");

root = new _cls_website0();
_cls_website0_instances.put(root, root);
  root.initialisation();
}catch(Exception ex)
{ex.printStackTrace();}
}

_cls_website0 parent; //to remain null - this class does not have a parent!
int no_automata = 1;
 public boolean userViewedAlerts =false ;
 public boolean loggedIn =false ;

public static void initialize(){}
//inheritance could not be used because of the automatic call to super()
//when the constructor is called...we need to keep the SAME parent if this exists!

public _cls_website0() {
}

public void initialisation() {
}

public static _cls_website0 _get_cls_website0_inst() { synchronized(_cls_website0_instances){
 return root;
}
}

public boolean equals(Object o) {
 if ((o instanceof _cls_website0))
{return true;}
else
{return false;}
}

public int hashCode() {
return 0;
}

public void _call(String _info, int... _event){
synchronized(_cls_website0_instances){
_performLogic_websiteProperty(_info, _event);
}
}

public void _call_all_filtered(String _info, int... _event){
}

public static void _call_all(String _info, int... _event){

_cls_website0[] a = new _cls_website0[1];
synchronized(_cls_website0_instances){
a = _cls_website0_instances.keySet().toArray(a);}
for (_cls_website0 _inst : a)

if (_inst != null) _inst._call(_info, _event);
}

public void _killThis(){
try{
if (--no_automata == 0){
synchronized(_cls_website0_instances){
_cls_website0_instances.remove(this);}
}
else if (no_automata < 0)
{throw new Exception("no_automata < 0!!");}
}catch(Exception ex){ex.printStackTrace();}
}

int _state_id_websiteProperty = 71;

public void _performLogic_websiteProperty(String _info, int... _event) {

_cls_website0.pw.println("[websiteProperty]AUTOMATON::> websiteProperty("+") STATE::>"+ _string_websiteProperty(_state_id_websiteProperty, 0));
_cls_website0.pw.flush();

if (0==1){}
else if (_state_id_websiteProperty==69){
		if (1==0){}
		else if ((_occurredEvent(_event,190/*viewMyAlerts*/)) && (loggedIn ==true )){
		userViewedAlerts =true ;
_cls_website0.pw .println ("User viewed My Alerts event: "+userViewedAlerts );

		_state_id_websiteProperty = 69;//moving to state loggedIn
		_goto_websiteProperty(_info);
		}
		else if ((_occurredEvent(_event,188/*logOut*/)) && (loggedIn ==true )){
		loggedIn =false ;
userViewedAlerts =false ;
_cls_website0.pw .println ("User has logged out");

		_state_id_websiteProperty = 71;//moving to state loggedOut
		_goto_websiteProperty(_info);
		}
}
else if (_state_id_websiteProperty==71){
		if (1==0){}
		else if ((_occurredEvent(_event,186/*goodLogin*/)) && (loggedIn ==false )){
		loggedIn =true ;
userViewedAlerts =true ;
_cls_website0.pw .println ("Login status: "+loggedIn );

		_state_id_websiteProperty = 69;//moving to state loggedIn
		_goto_websiteProperty(_info);
		}
		else if ((_occurredEvent(_event,184/*badLogin*/)) && (loggedIn ==false )){
		loggedIn =false ;
userViewedAlerts =false ;
_cls_website0.pw .println ("Bad login event. Login status: "+loggedIn );

		_state_id_websiteProperty = 71;//moving to state loggedOut
		_goto_websiteProperty(_info);
		}
}
}

public void _goto_websiteProperty(String _info){
_cls_website0.pw.println("[websiteProperty]MOVED ON METHODCALL: "+ _info +" TO STATE::> " + _string_websiteProperty(_state_id_websiteProperty, 1));
_cls_website0.pw.flush();
}

public String _string_websiteProperty(int _state_id, int _mode){
switch(_state_id){
case 69: if (_mode == 0) return "loggedIn"; else return "loggedIn";
case 71: if (_mode == 0) return "loggedOut"; else return "loggedOut";
case 70: if (_mode == 0) return "userViewedAlerts"; else return "userViewedAlerts";
default: return "!!!SYSTEM REACHED AN UNKNOWN STATE!!!";
}
}

public boolean _occurredEvent(int[] _events, int event){
for (int i:_events) if (i == event) return true;
return false;
}
}