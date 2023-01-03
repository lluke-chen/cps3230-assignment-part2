package aspects;

import larva.*;
public aspect _asp_alerts0 {

public static Object lock = new Object();

boolean initialized = false;

after():(staticinitialization(*)){
if (!initialized){
	initialized = true;
	_cls_alerts0.initialize();
}
}
before () : (call(* *.createAlert(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_alerts0.lock){

_cls_alerts0 _cls_inst = _cls_alerts0._get_cls_alerts0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 132/*createAlert*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 132/*createAlert*/);
}
}
before () : (call(* *.deleteAlerts(..)) && !cflow(adviceexecution()) && !cflow(within(larva.*))  && !(within(larva.*))) {

synchronized(_asp_alerts0.lock){

_cls_alerts0 _cls_inst = _cls_alerts0._get_cls_alerts0_inst();
_cls_inst._call(thisJoinPoint.getSignature().toString(), 134/*deleteAlerts*/);
_cls_inst._call_all_filtered(thisJoinPoint.getSignature().toString(), 134/*deleteAlerts*/);
}
}
}