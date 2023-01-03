package larva;


import java.util.LinkedHashMap;
import java.io.PrintWriter;

public class _cls_alerts0 implements _callable{

public static PrintWriter pw; 
public static _cls_alerts0 root;

public static LinkedHashMap<_cls_alerts0,_cls_alerts0> _cls_alerts0_instances = new LinkedHashMap<_cls_alerts0,_cls_alerts0>();
static{
try{
RunningClock.start();
pw = new PrintWriter("C:\\Users\\Luke\\workspace\\Alerts/src/output_alerts.txt");

root = new _cls_alerts0();
_cls_alerts0_instances.put(root, root);
  root.initialisation();
}catch(Exception ex)
{ex.printStackTrace();}
}

_cls_alerts0 parent; //to remain null - this class does not have a parent!
int no_automata = 1;
 public int alertsCount =0 ;

public static void initialize(){}
//inheritance could not be used because of the automatic call to super()
//when the constructor is called...we need to keep the SAME parent if this exists!

public _cls_alerts0() {
}

public void initialisation() {
}

public static _cls_alerts0 _get_cls_alerts0_inst() { synchronized(_cls_alerts0_instances){
 return root;
}
}

public boolean equals(Object o) {
 if ((o instanceof _cls_alerts0))
{return true;}
else
{return false;}
}

public int hashCode() {
return 0;
}

public void _call(String _info, int... _event){
synchronized(_cls_alerts0_instances){
_performLogic_alertsProperty(_info, _event);
}
}

public void _call_all_filtered(String _info, int... _event){
}

public static void _call_all(String _info, int... _event){

_cls_alerts0[] a = new _cls_alerts0[1];
synchronized(_cls_alerts0_instances){
a = _cls_alerts0_instances.keySet().toArray(a);}
for (_cls_alerts0 _inst : a)

if (_inst != null) _inst._call(_info, _event);
}

public void _killThis(){
try{
if (--no_automata == 0){
synchronized(_cls_alerts0_instances){
_cls_alerts0_instances.remove(this);}
}
else if (no_automata < 0)
{throw new Exception("no_automata < 0!!");}
}catch(Exception ex){ex.printStackTrace();}
}

int _state_id_alertsProperty = 59;

public void _performLogic_alertsProperty(String _info, int... _event) {

_cls_alerts0.pw.println("[alertsProperty]AUTOMATON::> alertsProperty("+") STATE::>"+ _string_alertsProperty(_state_id_alertsProperty, 0));
_cls_alerts0.pw.flush();

if (0==1){}
else if (_state_id_alertsProperty==58){
		if (1==0){}
		else if ((_occurredEvent(_event,132/*createAlert*/))){
		alertsCount ++;
_cls_alerts0.pw .println ("Alert created event. Alerts count: "+alertsCount );

		_state_id_alertsProperty = 58;//moving to state alertsNotEmpty
		_goto_alertsProperty(_info);
		}
		else if ((_occurredEvent(_event,134/*deleteAlerts*/))){
		alertsCount =0 ;
_cls_alerts0.pw .println ("Deleted alerts event. Alerts count: "+alertsCount );

		_state_id_alertsProperty = 59;//moving to state alertsEmpty
		_goto_alertsProperty(_info);
		}
}
else if (_state_id_alertsProperty==59){
		if (1==0){}
		else if ((_occurredEvent(_event,132/*createAlert*/))){
		alertsCount ++;
_cls_alerts0.pw .println ("Alert created event. Alerts count: "+alertsCount );

		_state_id_alertsProperty = 58;//moving to state alertsNotEmpty
		_goto_alertsProperty(_info);
		}
		else if ((_occurredEvent(_event,134/*deleteAlerts*/))){
		alertsCount =0 ;
_cls_alerts0.pw .println ("Deleted alerts event. Alerts count: "+alertsCount );

		_state_id_alertsProperty = 59;//moving to state alertsEmpty
		_goto_alertsProperty(_info);
		}
}
}

public void _goto_alertsProperty(String _info){
_cls_alerts0.pw.println("[alertsProperty]MOVED ON METHODCALL: "+ _info +" TO STATE::> " + _string_alertsProperty(_state_id_alertsProperty, 1));
_cls_alerts0.pw.flush();
}

public String _string_alertsProperty(int _state_id, int _mode){
switch(_state_id){
case 58: if (_mode == 0) return "alertsNotEmpty"; else return "alertsNotEmpty";
case 59: if (_mode == 0) return "alertsEmpty"; else return "alertsEmpty";
default: return "!!!SYSTEM REACHED AN UNKNOWN STATE!!!";
}
}

public boolean _occurredEvent(int[] _events, int event){
for (int i:_events) if (i == event) return true;
return false;
}
}