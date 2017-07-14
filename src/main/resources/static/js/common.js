/*
Globalization
Author:kain
*/
$.RS = {
    getString: function (key) {
        return this.entries[key];
    }
};
 $.formatstr = function (source, params) {
     if (arguments.length == 1)
         return function () {
             var args = $.makeArray(arguments);
             args.unshift(source);
             return $.format.apply(this, args);
         };
     if (arguments.length > 2 && params.constructor != Array) {
         params = $.makeArray(arguments).slice(1);
     }
     if (params.constructor != Array) {
         params = [params];
     }
     $.each(params, function (i, n) {
         source = source.replace(new RegExp("\\{" + i + "\\}", "g"), n);
     });
     return source;
 };

String.prototype.translate = function () { return $.RS.getString(this); }

/*
gridview全选
Author: liuyu
*/
function selectAll(sender, gvID) {
    var express = "#" + gvID + " input[name='chk']:enabled";
    var checkexpress = "#" + gvID + " input[name='chk'][checked=true]";
    $(express).attr("checked", sender.checked);
    $(express).each(function () {
        $(this).bind("click", function () {
            //实现全选或返选
            if (!this.checked) {
                sender.checked = this.checked;
            }
            else if ($(checkexpress).length == $(express).length)
                sender.checked = true;
        })
    });
};

/*
判断是gridview是否选择
Author: liuyu
*/
function checkIsSelected(gvID, msg) {
    var express = "#" + gvID + " input[name='chk']:checked:enabled";
    var selected = $(express).length;
    if (selected == 0) {
        xysalert(msg, "Note".translate(), 400, 200, '');
        return false;
    }
    return true;
}

/*
查询检查是否包含<、>、'、%、*
Author: liuyu
*/
function CheckQueryText(value) {

    var isCheck = (/'|<|>|%|\*/).test(value);
    if (value.length > 0 && isCheck) {

        xysalert("NoSpecialCharactersQuery".translate(), "Note".translate(), 400, 200, "");
        return false;
    }
    else {
        return true;
    }

};

// 检查输入的特殊字符串
function CheckInputText(value) {
    if (value.length == 0) {
        return true;
    }
    return !(/<|>|&lt|&gt/).test(value);
}

$().ready(function () {
    /*
    检查不能输入html标记
    Author: liuyu
    */
    function CheckHtml(value) {
        return !(/<|>|&lt|&gt/).test(value)
    };



    /*
    检查不能输入html标记
    Author: liuyu
    */
    jQuery.validator.addMethod('CheckHtml',
        function (value, element) {
            return this.optional(element) || CheckHtml(value);
        },
        "NoSpecialCharactersHTML".translate()
    //AlertMessages.NoSpecialCharactersHTML
    );

    /*
    检查下拉框是否选择
    Author: liuyu
    */
    jQuery.validator.addMethod('CheckSelect',
        function (value, element) {
            return value != "-1" && value != "";
        },
        "PleaseSelect".translate()
    );

    //验证只能输入字母，数字，下划线       
    jQuery.validator.addMethod("stringCheck", function (value, element) {
        return this.optional(element) || /^\w+$/i.test(value);
    },
        "NotAllowedCharacters".translate()
        );
})


/*
封装原生态confirm
Author: fangchenhua
Date:2011.4.25
*/
function xysconfirm(id, msg, title, width, height) {

    return confirm(msg);
}


/*
执行回调函数的方法 
Author: liuchuanyong
*/
function __xysDoPostBack(eventTarget, eventArgument) {
    var theform = document.forms[0];
    if (!theform.__EVENTTARGET) {
        $("<input type='hidden' name='__EVENTTARGET'>").appendTo(theform);
    }
    if (!theform.__EVENTARGUMENT) {
        $("<input type='hidden' name='__EVENTARGUMENT'>").appendTo(theform);
    }
    theform.__EVENTTARGET.value = eventTarget.split("$").join(":");
    theform.__EVENTARGUMENT.value = eventArgument;
    if ((typeof (theform.onsubmit) == "function") && theform.onsubmit() != false) {
        theform.submit();
    }
    else {
        theform.submit();
    }
}


/*
公共Alert方法 用于操作结果提醒
Author: liuchuanyong
*/
xysalert = function (msg, title, width, height, url) {
    window.top.xysalert(msg, title, width, height, url);
}

reSizeiFrame = function (iframe) {
    if (iframe && !window.opera) {
        iframe.style.display = "block";
        if (iframe.contentDocument && iframe.contentDocument.body.offsetHeight) {
            iframe.height = iframe.contentWindow.document.documentElement.scrollHeight; ; //设置iframe在火狐下高度
        }
        else if (iframe.Document && iframe.Document.body.scrollHeight) {
            iframe.height = iframe.Document.body.scrollHeight; //设置iframe在ie下的高度
        }
    }
    iframe.height = iframe.height < 500 ? 500 : iframe.height;
    if ($(iframe).attr("src") != '') {
        window.top.document.getElementById("mainFrame").height = iframe.height;
    }
}
/*
模拟模式弹出窗口
Author: liuchuanyong
*/
OpenWindow = function (url) {
    $("#IFRAME1").bind("load", function () {
        reSizeiFrame(this);
    });
    $("#MyFormLayer").children("#IFRAME1").attr("src", url);
    $("#MyFormLayer").show();
}
function getScrollTop() {
    var scrollTop = 0;
    if (document.documentElement && document.documentElement.scrollTop) {
        scrollTop = document.documentElement.scrollTop;
    }
    else if (document.body) {
        scrollTop = document.body.scrollHeight;
    }
    return scrollTop;
}
function getScrollLeft() {
    var scrollLeft = 0;
    if (document.documentElement && document.documentElement.scrollLeft) {
        scrollTop = document.documentElement.scrollLeft;
    }
    else if (document.body) {
        scrollTop = document.body.scrollWidth;
    }
    return scrollTop;
}

/*
关闭弹出窗口
Author: liuchuanyong
*/
CloseWindow = function (id) {
    $("#MyFormLayer").hide();
    $("#MyFormLayer").children("#IFRAME1").attr("src", "");
    if (typeof (id) != "undefined" && id != "")
        __xysDoPostBack(id, '');
    try {
        clientUnity.setFrameHeight();
    }
    catch (e) {
    }
}


function bodyLoad() {
    exploreResize();
}
/*
浏览器改变大小弹出层自适应方法
Author: liuchuanyong
*/
function exploreResize() {

    try {
        //debugger;
        var formid = $("form").first();
        var scrollHeight = getScrollTop();
        var height = scrollHeight > $('#main_iframe', window.top.document).height() ? scrollHeight : $('#main_iframe', window.top.document).height();
        var width = getScrollLeft() > $('#main_iframe', window.top.document).width() ? $('#main_iframe', window.top.document).width() : getScrollLeft();
        //$("#MyFormLayer").width(width);
        //$(formid).width(width);
        //$(formid).height(height);
        $("#MyFormLayer").width($('#main_iframe', window.top.document).width());
        $("#MyFormLayer").height(height);
    } catch (e) { }
}
/*
js多语言取翻译
Author: liuchuanyong
*/
TranslationEntry = function (key) {
    return key.translate();
    /*
    var ret = key;
    var obj = { Source_word: "" };
    obj.Source_word = $.trim(key);
    $.ajax({
    type: 'POST',
    url: '/Common/AjaxValidator.ashx?validatorType=TranslationEntry',
    async: false,
    data: obj,
    success: function (data, textStatus) {
    try {
    ret = data;
    }
    catch (e) {

    }
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {

    }
    });
    return ret;
    */
}
/*
公共弹出DIV嵌套iframe的模态窗体
Author: liuchuanyong
*/
xysShowModal = function (title, url, width, height, callback) {

    var w_title = TranslationEntry(title);
    var $browser = $.browser;

    if ($browser.msie)
    //        $("<div style='width:100%;overflow:auto' id='divchooseFrame'><iframe  scrolling='auto' width='100%' height='100%'  frameborder=0  id='ChooseFrame' src='" + url + "' /></div>").dialog({ autoOpen: true, width: width, height: height || 700, showTitle: false, draggable: false, bgiframe: true, modal: true, title: w_title, close: callback });
        $("<div style='width:" + width + "px;height:100%;' id='divchooseFrame'><iframe  scrolling='no' width='100%' height='100%'  frameborder=0  id='ChooseFrame' src='" + url + "' /></div>").dialog({ autoOpen: true, width: width, height: height || 700, showTitle: false, draggable: false, bgiframe: true, modal: true, title: w_title, close: callback });
    else {
        if (height) {
            height = height - 180;
        }
        $("<div style='width:100%;' id='divchooseFrame'><iframe  scrolling='no' width='100%' height='100%'  frameborder=0  id='ChooseFrame' src='" + url + "' /></div>").dialog({ autoOpen: true, width: width, height: height || 500, showTitle: false, draggable: false, bgiframe: true, modal: true, title: w_title, close: callback });
    }
}
xysCloseModal = function (call) {
    $('div').dialog('close');
}

function SetWinHeight(obj) {
    var win = obj;
    if (document.getElementById) {
        if (win && !window.opera) {
            if (win.contentDocument && win.contentDocument.body.offsetHeight)

                win.height = win.contentDocument.body.offsetHeight;
            else if (win.Document && win.Document.body.scrollHeight)
                win.height = win.Document.body.scrollHeight;
        }
        //var width = getScrollLeft() > $('#main_iframe', window.top.document).width() ? $('#main_iframe', window.top.document).width() : getScrollLeft();
        //win.width = width;
    }
}

/*
获取js日期对象 格式：2011-02-23
Author: wenan
*/
parseDate = function (da) {
    if (da == "") {
        return null;
    }
    var yp = da.indexOf('-', 0);
    mp = da.indexOf('-', yp + 1);
    var y = da.substr(0, yp);
    m = da.substr(yp + 1, mp - yp - 1),
    d = da.substr(mp + 1, da.length - mp);
    return new Date(y, m, d);
}

/*
获取js日期对象 2011-02-23
Author: wenan
*/
formatDate = function (d) {
    if (d == "NaN") {
        return "";
    }
    var year = d.getYear();
    if (d.getMonth() == 0) {
        year -= 1;
    }
    return year + "-" + formatDateNum(d.getMonth()) + "-" + formatDateNum(d.getDate());
}
formatDateNum = function (num) {
    if (num == 0) {
        return "12";
    }
    if (num >= 10) {
        return num;
    }
    return "0" + num;
}

/*
设置页面默认控件焦点
Author: wenan
*/
defaultFocus = function (controlId) {
    try {
        $("#" + controlId).focus();
    } catch (e) {
    }
}


function getMemberSelectedIndex() {
    var index = 0;
    return Math.max(index, parseInt($("#_pi").val()));
}

function parseMemberUrl(url) {
    var index = getMemberSelectedIndex();
    var aindex = url.indexOf(".aspx#");
    var urlEnd = "";
    if (aindex > 0) {
        var firstUrl = url.substr(0, aindex) +
".aspx";
        urlEnd = url.substr(aindex + 5,
url.length - aindex - 5);
        url = firstUrl;
    }
    if (url.indexOf("?") > -1)
        return url + "&_pi=" + index + urlEnd;
    else
        return url + "?_pi=" + index + urlEnd;
}

function setFrameLocation(url) {
    if (url && url != "")
        url = parseMemberUrl(url);

    $("#mainFrame").attr("src", url);
}

var clientUnity = function () {
    var redirect = function (url) {
        window.top.DataManager.getInstance().parseUrl(url);
        window.top.window.MenuBarInstance.init();
        return false;
    },
    redirectToPersional = function (_pi, url) {
        var url = _persionalUrl + _pi + "&url=" + url;
        window.open(url);
        return false;
    },
    redirectToPersionalByMenuId = function (_pi, url, menuId) {
        var url = _persionalUrl + _pi + "&menuId=" + menuId + "&url=" + url;
        window.open(url);
        return false;
    },
    expandMenu = function (menuId) {
        window.top.DataManager.getInstance().parseId(menuId);
        window.top.IFrameContainer.getInstance().locked = true;
        window.top.window.MenuBarInstance.init();
    },
    expandMenuLoadUrl = function (menuId, url) {
        expandMenu(menuId);
        window.top.IFrameContainer.getInstance().load(url);
    },
    openWindow = function (url, title, width, height, isModal) {
        var features = "toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, copyhistory=no";
        if (width != null || width != undefined) {
            features += ",width=" + width;
        }
        if (height != null || height != undefined) {
            features += ",height=" + height;
        }
        if (isModal) {
            window.showModalDialog(url, title, features)
        }
        else {
            window.open(url, title, features);
        }
        return false;
    },
    resizeFrame = function () {
        var iframe = window.top.document.getElementById("mainFrame")
        if (iframe && !window.opera) {
            iframe.style.display = "block";
            if ($.browser.msie) {
                try {
                    iframe.height = iframe.Document.body.scrollHeight; //设置iframe在ie下的高度
                }
                catch (e) {
                    iframe.height = iframe.contentWindow.document.documentElement.scrollHeight; //IE9
                }
            }
            else {
                iframe.height = iframe.contentWindow.document.documentElement.scrollHeight; //设置iframe在火狐下高度
            }
        }
    },
    setFrameHeight = function () {
        try {
            window.top.document.getElementById("mainFrame").height = window.top._memoryHeight;
        } catch (e) {
            window.top.document.getElementsById("mainFrame").height = 500;
        }
    },
    resizeOpenWindow = function () {
        var $openDiv = $(parent.document.getElementById("MyFormLayer"));

        var iframe = window.top.document.getElementById("mainFrame");
        var hiddenIframe = parent.document.getElementById("IFRAME1");

        if (hiddenIframe && !window.opera) {
            hiddenIframe.style.display = "block";
            if ($.browser.msie) {
                try {
                    iframe.height = hiddenIframe.Document.body.scrollHeight; //设置iframe在ie下的高度
                }
                catch (e) {
                    iframe.height = hiddenIframe.contentWindow.document.documentElement.scrollHeight; //IE9
                }
            }
            else {
                iframe.height = hiddenIframe.contentWindow.document.documentElement.scrollHeight; //设置iframe在火狐下高度
            }
            hiddenIframe.height = iframe.height;
            return true;
        }
        return false;

    }
    _persionalUrl = "/Portal/Doctor/Personal.aspx?_pi=";
    return {
        redirect: function (url) {
            redirect(url);
        }, //用于同一框架页中 不同菜单下的 页面跳转 控制菜单定位
        redirectToPersional: function (_pi, url) {
            redirectToPersional(_pi, url);
            return false;
        }, //用于从群体模式 跳转到 个人模式
        expandMenu: function (menuId) {
            expandMenu(menuId);
        },
        openWindow: function (url, title, width, height, isModal) {
            return openWindow(url, title, width, height, isModal);
        }, //用于打开新的窗口
        resizeFrame: function () {
            resizeFrame();
        },
        redirectToPersionalByMenuId: function (_pi, url, menuId) {
            redirectToPersionalByMenuId(_pi, url, menuId);
        },
        expandMenuLoadUrl: function (menuId, url) {
            expandMenuLoadUrl(menuId, url);
        },
        resizeOpenWindow: function () {
            if (!resizeOpenWindow()) {
                resizeFrame();
            }
            return true;
        },
        setFrameHeight: function () {
            setFrameHeight();
        }
    };
} ();

//TreeView_ToggleNodeFun方法,树节点展开方法重写
// 加入clientUnity.resizeFrame();
function iniTreeView_ToggleNodeFun() {
    window.TreeView_ToggleNode = function (data, index, node, lineType, children) {
        if (!data) {
            return;
        }
        var img = node.childNodes[0];
        var newExpandState;
        try {
            if (children.style.display == "none") {
                children.style.display = "block";
                newExpandState = "e";
                if ((typeof (img) != "undefined") && (img != null)) {
                    if (lineType == "l") {
                        img.src = data.images[15];
                    }
                    else if (lineType == "t") {
                        img.src = data.images[12];
                    }
                    else if (lineType == "-") {
                        img.src = data.images[18];
                    }
                    else {
                        img.src = data.images[5];
                    }
                    img.alt = data.collapseToolTip.replace(/\{0\}/, TreeView_GetNodeText(node));
                }
            }
            else {
                children.style.display = "none";
                newExpandState = "c";
                if ((typeof (img) != "undefined") && (img != null)) {
                    if (lineType == "l") {
                        img.src = data.images[14];
                    }
                    else if (lineType == "t") {
                        img.src = data.images[11];
                    }
                    else if (lineType == "-") {
                        img.src = data.images[17];
                    }
                    else {
                        img.src = data.images[4];
                    }
                    img.alt = data.expandToolTip.replace(/\{0\}/, TreeView_GetNodeText(node));
                }
            }
        }
        catch (e) { }
        data.expandState.value = data.expandState.value.substring(0, index) + newExpandState + data.expandState.value.slice(index + 1);
        clientUnity.resizeFrame();
    }
}



