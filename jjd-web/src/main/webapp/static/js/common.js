Date.prototype.pattern = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // �·�
		"d+" : this.getDate(), // ��
		"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, // Сʱ
		"H+" : this.getHours(), // Сʱ
		"m+" : this.getMinutes(), // ��
		"s+" : this.getSeconds(), // ��
		"q+" : Math.floor((this.getMonth() + 3) / 3), // ����
		"S" : this.getMilliseconds()
	// ����
	};
	var week = {
		"0" : "/u65e5",
		"1" : "/u4e00",
		"2" : "/u4e8c",
		"3" : "/u4e09",
		"4" : "/u56db",
		"5" : "/u4e94",
		"6" : "/u516d"
	};
	if (/(y+)/.test(fmt)) {
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	if (/(E+)/.test(fmt)) {
		fmt = fmt
				.replace(
						RegExp.$1,
						((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f"
								: "/u5468")
								: "")
								+ week[this.getDay() + ""]);
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
		}
	}
	return fmt;
}
// 合并2个对象
Array.prototype.combine = function() {
	var arr = [];
	try {
		if (this[0].length == 3) {
			for (var i = 0; i < this.length; i++) {
				var obj = {};
				for ( var l in this[i][2]) {
					obj[l] = this[i][2][l];
				}
				for ( var k in this[i][1]) {
					obj[k] = this[i][1][k];
				}
				for ( var j in this[i][0]) {
					obj[j] = this[i][0][j];
				}
				arr.push(obj);
			}
		} else {
			for (var i = 0; i < this.length; i++) {
				var obj = {};
				for ( var k in this[i][1]) {
					obj[k] = this[i][1][k];
				}
				for ( var j in this[i][0]) {
					obj[j] = this[i][0][j];
				}
				arr.push(obj);
			}
		}
		return arr;
	} catch (e) {
	}
}
// 返回2015-05-12 00:00:00格式
$.fn.datebox.defaults.formatter = function(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d);
};

$.fn.datebox.defaults.parser = function(s) {
	if (s) {
		var a = s.split('-');
		var d = new Date(parseInt(a[0]), parseInt(a[1]) - 1, parseInt(a[2]));
		return d;
	} else {
		return new Date();
	}
};
// 添加新的验证方法
jQuery.validator
		.addMethod(
				"phone",
				function(value, element, param) {
					var length = value.length;
					var phone = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;
					return this.optional(element)
							|| (length == 11 && phone.test(value));
				}, "请正确填写您的电话号码！");
jQuery.validator.addMethod("chinese", function(value, element, param) {
	var chinese = /^[\u4e00-\u9fa5]{0,}$/;
	return this.optional(element) || chinese.test(value);
}, "请输入中文！");
jQuery.validator.addMethod("identify", function(value, element, param) {
	var identify = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
	return this.optional(element) || identify.test(value);
}, "请输入正确的身份证号码！");
$(function() {
	// 左侧菜单高度
	var hs = $(window).height() - 62 - 25, rightHeight = $(".right-content")
			.height();
	(hs > rightHeight) ? $(".left-menu").height(hs) : $(".left-menu").height(
			rightHeight);
});

$(function() {

})

function getAllSelectedRows(tableId) {
	var josnArray = new Array();
	$("#" + tableId).find("input[name='ids']:checked").each(function() {
		josnArray.push($(this).val());
	});
	return josnArray;
}

function closeWin() {
	var i = parent.layer.getFrameIndex();
	parent.layer.close(i);
}

var layerDailog = function(msg, success) {
	// $.layer({
	// closeBtn : [ 0, false ],
	// shade : [ 0 ],
	// area : [ 'auto', 'auto' ],
	// dialog : {
	// msg : msg,
	// btns : 2,
	// type : 4,
	// btn : [ '确定', '取消' ],
	// yes : function(index) {
	// if (success) {
	// parent.query();
	// layer.close(index);
	// closeWin();
	// } else {
	// layer.close(index);
	// }
	// }
	// }
	// });

}