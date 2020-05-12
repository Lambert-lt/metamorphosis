/**
 * jquery订阅发布插件
 * @author liucg
 */
(function ($) {

    var opts = Object.prototype.toString;
    $.global = this;

    $.isString = jQuery.fn.isString = function (/*anything*/it) {
        return (typeof it == "string" || it instanceof String); // Boolean
    }

    $.isArray = jQuery.fn.isArray = function (/*anything*/it) {
        return it && (it instanceof Array || typeof it == "array"); // Boolean
    }

    $.isFunction = jQuery.fn.isFunction = function (/*anything*/it) {
        return opts.call(it) === "[object Function]";
    };

    $.isObject = jQuery.fn.isObject = function (/*anything*/it) {
        return it !== undefined &&
            (it === null || typeof it == "object" || $.isArray(it) || $.isFunction(it)); // Boolean
    }

    $.isArrayLike = jQuery.fn.isArrayLike = function (/*anything*/it) {
        return it && it !== undefined && // Boolean
            !$.isString(it) && !$.isFunction(it) &&
            !(it.tagName && it.tagName.toLowerCase() == 'form') &&
            ($.isArray(it) || isFinite(it.length));
    }

    $.isAlien = jQuery.fn.isAlien = function (/*anything*/it) {
        return it && !$.isFunction(it) && /\{\s*\[native code\]\s*\}/.test(String(it)); // Boolean
    }

    $._listener = {
        // create a dispatcher function
        getDispatcher: function () {
            return function () {
                var ap = Array.prototype, c = arguments.callee, ls = c._listeners, t = c.target;
                // return value comes from original target function
                var r = t && t.apply(this, arguments);
                // make local copy of listener array so it is immutable during processing
                var i, lls;
                lls = [].concat(ls);

                // invoke listeners after target function
                for (i in lls) {
                    if (!(i in ap)) {
                        lls[i].apply(this, arguments);
                    }
                }
                // return value comes from original target function
                return r;
            };
        },
        // add a listener to an object
        add: function (/*Object*/source, /*String*/method, /*Function*/listener) {
            source = source || $.global;
            var f = source[method];
            if (!f || !f._listeners) {
                var d = $._listener.getDispatcher();
                d.target = f;
                // dispatcher holds a list of listeners
                d._listeners = [];
                // redirect source to dispatcher
                f = source[method] = d;
            }
            return f._listeners.push(listener); /*Handle*/
        },
        remove: function (/*Object*/source, /*String*/method, /*Handle*/handle) {
            var f = (source || $.global)[method];
            if (f && f._listeners && handle--) {
                delete f._listeners[handle];
            }
        }
    };

    $.connect = jQuery.fn.connect = function (/*Object|null*/obj,
    /*String*/event,
    /*Object|null*/context,
    /*String|Function*/method,
    /*Boolean?*/dontFix) {
        var a = arguments, args = [], i = 0;
        args.push($.isString(a[0]) ? null : a[i++], a[i++]);
        var a1 = a[i + 1];
        args.push($.isString(a1) || $.isFunction(a1) ? a[i++] : null, a[i++]);
        for (var l = a.length; i < l; i++) { args.push(a[i]); }
        return $._connect.apply(this, args); /*Handle*/
    }

    $._connect = function (obj, event, context, method) {
        var l = $._listener, h = l.add(obj, event, $.hitch(context, method));
        return [obj, event, h, l]; // Handle
    }

    $.disconnect = jQuery.fn.disconnect = function (/*Handle*/handle) {
        if (handle && handle[0] !== undefined) {
            $._disconnect.apply(this, handle);
            delete handle[0];
        }
    }

    $._disconnect = function (obj, event, handle, listener) {
        listener.remove(obj, event, handle);
    }

    $._hitchArgs = function (scope, method /*,...*/) {
        var pre = $._toArray(arguments, 2);
        var named = $.isString(method);
        return function () {
            var args = $._toArray(arguments);
            var f = named ? (scope || $.global)[method] : method;
            return f && f.apply(scope || this, pre.concat(args)); // mixed
        } // Function
    }

    var efficient = function (obj, offset, startWith) {
        return (startWith || []).concat(Array.prototype.slice.call(obj, offset || 0));
    };

    $._toArray =
    /*$.isIE ? function (obj) */
                 navigator.userAgent.toLowerCase().indexOf("ie")>-1 ? function (obj) {
                     return ((obj.item) ? slow : efficient).apply(this, arguments);
                 } :
                efficient;

    $.hitch = jQuery.fn.hitch = function (/*Object*/scope, /*Function|String*/method /*,...*/) {

        if (arguments.length > 2) {
            return $._hitchArgs.apply($, arguments); // Function
        }
        if (!method) {
            method = scope;
            scope = null;
        }
        if ($.isString(method)) {
            scope = scope || $.global;
            if (!scope[method]) { throw (['$.hitch: scope["', method, '"] is null (scope="', scope, '")'].join('')); }
            return function () { return scope[method].apply(scope, arguments || []); }; // Function
        }
        return !scope ? method : function () { return method.apply(scope, arguments || []); }; // Function
    }

    $._topics = {};

    $.subscribe = jQuery.fn.subscribe = function (/*String*/topic, /*Object|null*/context, /*String|Function*/method) {
        return [topic, $._listener.add($._topics, topic, $.hitch(context, method))]; /*Handle*/
    }

    $.unsubscribe = jQuery.fn.unsubscribe = function (/*Handle*/handle) {
        if (handle) {
            $._listener.remove($._topics, handle[0], handle[1]);
        }
    }

    $.publish = jQuery.fn.publish = function (/*String*/topic, /*Array*/args) {

        var f = $._topics[topic];
        if (f) {
            f.apply(this, args || []);
        }
    }

    $.connectPublisher = jQuery.fn.connectPublisher = function (    /*String*/topic,
    /*Object|null*/obj,
    /*String*/event) {

        var pf = function () { $.publish(topic, arguments); }
        return event ? $.connect(obj, event, pf) : $.connect(obj, pf); //Handle
    };


})(jQuery);

//测试预订信息
$.subscribe("test", this, function(msg){
	alert(msg);
});

//测试预订信息
$.subscribe("SYS_OFFLINE", this, function(msg){
	$.ajax({
		type: "GET",
		url: $.context + "/sys/Security/stop.cute",
		data : {"sessionId":$.sessionId},
	});
});

function listenerOut(){
	$.ajax( {
	   type: "GET",
	   url : $.context + "/comm/RtcServlet",
	   timeout : 30*15*1000,
	   dataType:"json",
	   data : {"_t" : new Date().getTime()},
	   success: function(data){
		   $.each(data, function(i,msg){      
				$.publish(msg.msgType,msg);             //发布已订阅的消息
		   }); 

	   },
	   error:function(){

	   }
	});
}
