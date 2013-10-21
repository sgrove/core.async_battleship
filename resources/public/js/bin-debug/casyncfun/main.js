goog.provide('casyncfun.main');
goog.require('cljs.core');
goog.require('cljs.core.async');
goog.require('cljs.core.async');
goog.require('cljs.core.async');
goog.require('clojure.browser.repl');
goog.require('clojure.browser.repl');
console.log("Running ClojureScript inside of casyncfun");
casyncfun.main.rc = cljs.core.async.chan.call(null);
var c__5180__auto___5942 = cljs.core.async.chan.call(null,1);cljs.core.async.impl.dispatch.run.call(null,(function (){var f__5181__auto__ = (function (){var switch__5108__auto__ = (function (state_5932){var state_val_5933 = (state_5932[1]);if((state_val_5933 === 4))
{var inst_5926 = (state_5932[2]);var inst_5927 = console.log("Message received: ",inst_5926);var state_5932__$1 = (function (){var statearr_5934 = state_5932;(statearr_5934[5] = inst_5927);
return statearr_5934;
})();var statearr_5935_5943 = state_5932__$1;(statearr_5935_5943[2] = null);
(statearr_5935_5943[1] = 2);
return new cljs.core.Keyword(null,"recur","recur",1122293407);
} else
{if((state_val_5933 === 3))
{var inst_5930 = (state_5932[2]);var state_5932__$1 = state_5932;return cljs.core.async.impl.ioc_helpers.return_chan.call(null,state_5932__$1,inst_5930);
} else
{if((state_val_5933 === 2))
{var inst_5924 = console.log("Inside");var state_5932__$1 = (function (){var statearr_5936 = state_5932;(statearr_5936[6] = inst_5924);
return statearr_5936;
})();return cljs.core.async.impl.ioc_helpers.take_BANG_.call(null,state_5932__$1,4,casyncfun.main.rc);
} else
{if((state_val_5933 === 1))
{var inst_5922 = console.log("Outside");var state_5932__$1 = (function (){var statearr_5937 = state_5932;(statearr_5937[7] = inst_5922);
return statearr_5937;
})();var statearr_5938_5944 = state_5932__$1;(statearr_5938_5944[2] = null);
(statearr_5938_5944[1] = 2);
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
var state_machine__5109__auto____0 = (function (){var statearr_5940 = (new Array(8));(statearr_5940[0] = state_machine__5109__auto__);
(statearr_5940[1] = 1);
return statearr_5940;
});
var state_machine__5109__auto____1 = (function (state_5932){while(true){
var result__5110__auto__ = switch__5108__auto__.call(null,state_5932);if((result__5110__auto__ === new cljs.core.Keyword(null,"recur","recur",1122293407)))
{{
continue;
}
} else
{return result__5110__auto__;
}
break;
}
});
state_machine__5109__auto__ = function(state_5932){
switch(arguments.length){
case 0:
return state_machine__5109__auto____0.call(this);
case 1:
return state_machine__5109__auto____1.call(this,state_5932);
}
throw(new Error('Invalid arity: ' + arguments.length));
};
state_machine__5109__auto__.cljs$core$IFn$_invoke$arity$0 = state_machine__5109__auto____0;
state_machine__5109__auto__.cljs$core$IFn$_invoke$arity$1 = state_machine__5109__auto____1;
return state_machine__5109__auto__;
})()
;})(switch__5108__auto__))
})();var state__5182__auto__ = (function (){var statearr_5941 = f__5181__auto__.call(null);(statearr_5941[cljs.core.async.impl.ioc_helpers.USER_START_IDX] = c__5180__auto___5942);
return statearr_5941;
})();return cljs.core.async.impl.ioc_helpers.run_state_machine_wrapped.call(null,state__5182__auto__);
}));
cljs.core.async.put_BANG_.call(null,casyncfun.main.rc,"First message");
cljs.core.async.put_BANG_.call(null,casyncfun.main.rc,"Second message");
