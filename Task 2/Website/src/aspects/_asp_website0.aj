package aspects;

import larva.*;
public aspect _asp_website0 {

public static Object lock = new Object();

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_website0.initialize();
}
}
before () : (call(* *.goodLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_website0.lock){

_cls_website0 _cls_inst = _cls_website0._get_cls_website0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 186/*goodLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 186/*goodLogin*/);
}
}
before () : (call(* *.badLogin(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_website0.lock){

_cls_website0 _cls_inst = _cls_website0._get_cls_website0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 184/*badLogin*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 184/*badLogin*/);
}
}
before () : (call(* *.logOut(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_website0.lock){

_cls_website0 _cls_inst = _cls_website0._get_cls_website0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 188/*logOut*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 188/*logOut*/);
}
}
before () : (call(* *.viewMyAlerts(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_website0.lock){

_cls_website0 _cls_inst = _cls_website0._get_cls_website0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 190/*viewMyAlerts*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 190/*viewMyAlerts*/);
}
}
}