goog.provide('casyncfun.main');
goog.require('cljs.core');
goog.require('cljs.core.async');
goog.require('cljs.core.async');
goog.require('cljs.core.async');
goog.require('clojure.browser.repl');
goog.require('clojure.browser.repl');
console.log("Running ClojureScript inside of casyncfun");
casyncfun.main.nrepl = (function nrepl(){return clojure.browser.repl.connect.call(null,"http://localhost:9000/repl");
});
goog.exportSymbol('casyncfun.main.nrepl', casyncfun.main.nrepl);
window.nrepl = casyncfun.main.nrepl;
casyncfun.main.rc = cljs.core.async.chan.call(null);
casyncfun.main.c2 = cljs.core.async.chan.call(null,100);
casyncfun.main.counter = cljs.core.atom.call(null,0);
casyncfun.main.mark_status_BANG_ = (function mark_status_BANG_(status){cljs.core.swap_BANG_.call(null,casyncfun.main.counter,cljs.core.inc);
var el = document.getElementById("status");var text = [cljs.core.str(el.innerHTML),cljs.core.str("<li>"),cljs.core.str(cljs.core.deref.call(null,casyncfun.main.counter)),cljs.core.str(". "),cljs.core.str(status),cljs.core.str("</li>")].join('');return el.innerHTML = text;
});
casyncfun.main.test = (function test(){casyncfun.main.mark_status_BANG_.call(null,"CLJS Loaded");
var c__5180__auto___5982 = cljs.core.async.chan.call(null,1);cljs.core.async.impl.dispatch.run.call(null,(function (){var f__5181__auto__ = (function (){var switch__5108__auto__ = (function (state_5963){var state_val_5964 = (state_5963[1]);if((state_val_5964 === 4))
{var inst_5956 = (state_5963[2]);var inst_5957 = [cljs.core.str("Message received: "),cljs.core.str(inst_5956)].join('');var inst_5958 = casyncfun.main.mark_status_BANG_.call(null,inst_5957);var state_5963__$1 = (function (){var statearr_5965 = state_5963;(statearr_5965[5] = inst_5958);
return statearr_5965;
})();var statearr_5966_5983 = state_5963__$1;(statearr_5966_5983[2] = null);
(statearr_5966_5983[1] = 2);
return new cljs.core.Keyword(null,"recur","recur",1122293407);
} else
{if((state_val_5964 === 3))
{var inst_5961 = (state_5963[2]);var state_5963__$1 = state_5963;return cljs.core.async.impl.ioc_helpers.return_chan.call(null,state_5963__$1,inst_5961);
} else
{if((state_val_5964 === 2))
{var inst_5954 = casyncfun.main.mark_status_BANG_.call(null,"Inside, never gets here.");var state_5963__$1 = (function (){var statearr_5967 = state_5963;(statearr_5967[6] = inst_5954);
return statearr_5967;
})();return cljs.core.async.impl.ioc_helpers.take_BANG_.call(null,state_5963__$1,4,casyncfun.main.rc);
} else
{if((state_val_5964 === 1))
{var inst_5952 = casyncfun.main.mark_status_BANG_.call(null,"Outside");var state_5963__$1 = (function (){var statearr_5968 = state_5963;(statearr_5968[7] = inst_5952);
return statearr_5968;
})();var statearr_5969_5984 = state_5963__$1;(statearr_5969_5984[2] = null);
(statearr_5969_5984[1] = 2);
return new cljs.core.Keyword(null,"recur","recur",1122293407);
} else
{return null;
}
}
}
}
});return ((function (switch__5108__auto__){
return (function() {
var state_machine__5109__auto__ = null;
var state_machine__5109__auto____0 = (function (){var statearr_5971 = (new Array(8));(statearr_5971[0] = state_machine__5109__auto__);
(statearr_5971[1] = 1);
return statearr_5971;
});
var state_machine__5109__auto____1 = (function (state_5963){while(true){
var result__5110__auto__ = switch__5108__auto__.call(null,state_5963);if((result__5110__auto__ === new cljs.core.Keyword(null,"recur","recur",1122293407)))
{{
continue;
}
} else
{return result__5110__auto__;
}
break;
}
});
state_machine__5109__auto__ = function(state_5963){
switch(arguments.length){
case 0:
return state_machine__5109__auto____0.call(this);
case 1:
return state_machine__5109__auto____1.call(this,state_5963);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
state_machine__5109__auto__.cljs$core$IFn$_invoke$arity$0 = state_machine__5109__auto____0;
state_machine__5109__auto__.cljs$core$IFn$_invoke$arity$1 = state_machine__5109__auto____1;
return state_machine__5109__auto__;
})()
;})(switch__5108__auto__))
})();var state__5182__auto__ = (function (){var statearr_5972 = f__5181__auto__.call(null);(statearr_5972[cljs.core.async.impl.ioc_helpers.USER_START_IDX] = c__5180__auto___5982);
return statearr_5972;
})();return cljs.core.async.impl.ioc_helpers.run_state_machine_wrapped.call(null,state__5182__auto__);
}));
var c__5180__auto___5985 = cljs.core.async.chan.call(null,1);cljs.core.async.impl.dispatch.run.call(null,(function (){var f__5181__auto__ = (function (){var switch__5108__auto__ = (function (state_5977){var state_val_5978 = (state_5977[1]);if((state_val_5978 === 2))
{var inst_5974 = (state_5977[2]);var inst_5975 = casyncfun.main.mark_status_BANG_.call(null,inst_5974);var state_5977__$1 = state_5977;return cljs.core.async.impl.ioc_helpers.return_chan.call(null,state_5977__$1,inst_5975);
} else
{if((state_val_5978 === 1))
{var state_5977__$1 = state_5977;return cljs.core.async.impl.ioc_helpers.take_BANG_.call(null,state_5977__$1,2,casyncfun.main.c2);
} else
{return null;
}
}
});return ((function (switch__5108__auto__){
return (function() {
var state_machine__5109__auto__ = null;
var state_machine__5109__auto____0 = (function (){var statearr_5980 = (new Array(5));(statearr_5980[0] = state_machine__5109__auto__);
(statearr_5980[1] = 1);
return statearr_5980;
});
var state_machine__5109__auto____1 = (function (state_5977){while(true){
var result__5110__auto__ = switch__5108__auto__.call(null,state_5977);if((result__5110__auto__ === new cljs.core.Keyword(null,"recur","recur",1122293407)))
{{
continue;
}
} else
{return result__5110__auto__;
}
break;
}
});
state_machine__5109__auto__ = function(state_5977){
switch(arguments.length){
case 0:
return state_machine__5109__auto____0.call(this);
case 1:
return state_machine__5109__auto____1.call(this,state_5977);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
state_machine__5109__auto__.cljs$core$IFn$_invoke$arity$0 = state_machine__5109__auto____0;
state_machine__5109__auto__.cljs$core$IFn$_invoke$arity$1 = state_machine__5109__auto____1;
return state_machine__5109__auto__;
})()
;})(switch__5108__auto__))
})();var state__5182__auto__ = (function (){var statearr_5981 = f__5181__auto__.call(null);(statearr_5981[cljs.core.async.impl.ioc_helpers.USER_START_IDX] = c__5180__auto___5985);
return statearr_5981;
})();return cljs.core.async.impl.ioc_helpers.run_state_machine_wrapped.call(null,state__5182__auto__);
}));
cljs.core.async.put_BANG_.call(null,casyncfun.main.c2,"My message");
cljs.core.async.put_BANG_.call(null,casyncfun.main.rc,"First message");
cljs.core.async.put_BANG_.call(null,casyncfun.main.rc,"Second message");
cljs.core.async.put_BANG_.call(null,casyncfun.main.c2,"Another message");
cljs.core.async.put_BANG_.call(null,casyncfun.main.rc,"First message");
return cljs.core.async.put_BANG_.call(null,casyncfun.main.rc,"Second message");
});
window.onload = casyncfun.main.test;
