(function($){
	$.altmodal=function(modalLabel, modalBody,btnconfirm,btncancel, callback) {
		$("#myModal").remove();
		$('body').append('<div id="myModal"></div>');
		var modal = $("#myModal");
		modal.attr("class", "modal hide fade");
		modal.attr('tabindex', '-1');
		modal.attr('role', 'dialog');
		modal.attr('aria-labelledby', 'myModalLabel');
		modal.attr('aria-hidden', 'true');
		
		modal
				.html('<div class="modal-header">'
						+ '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>'
						+ '<h4 id="myLabel">'
						+ modalLabel
						+ '</h4>'
						+ '</div>'
						+ '<div class="modal-body">'
						+ '<p>'
						+ modalBody
						+ '</p>'
						+ '</div>'
						+ '<div class="modal-footer">'
						+ '<button class="btn btn-primary">确定</button>'
						+ '<button class="btn btn-cancel" data-dismiss="modal" aria-hidden="true">取消</button>'
						+ '</div>')
						
		
		if(typeof btnconfirm == 'function'){
			callback=btnconfirm;
		}else if(typeof btnconfirm == 'string'&&btnconfirm!=""){
			$('.btn.btn-primary').html(btnconfirm);
		}
		if(typeof btncancel == 'function'){
			callback=btncancel;
		}else if(typeof btncancel == 'string'&&btncancel!=""){
			$('.btn.btn-cancel').html(btncancel);
		}
		$('.btn.btn-primary').bind('click', function() {
			modal.on('hidden', callback);
			modal.modal('hide');
		});
		modal.modal({
			backdrop : true,
			keyboard : true
		});

	}
	$.sucmodal=function( modalLabel, modalBody,timeout, callback) {
		$("#myModal").remove();
		$('body').append('<div id="myModal"></div>');
		var modal = $("#myModal");
		modal.attr("class", "modal hide fade");
		modal.attr('tabindex', '-1');
		modal.attr('role', 'dialog');
		modal.attr('aria-labelledby', 'myModalLabel');
		modal.attr('aria-hidden', 'true');
		modal
				.html('<div class="modal-header">'
						+ '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>'
						+ '<h4 id="myLabel">'
						+ modalLabel
						+ '</h4>'
						+ '</div>'
						+ '<div class="modal-body">'
						+ '<p>'
						+ modalBody
						+ '</p>'
						+ '</div>'
						+ '<div class="modal-footer">'
						+ '</div>')
		
		modal.modal({
			backdrop : false,
			keyboard : true
		});
		if(typeof timeout == 'function'){
			callback=timeout;
			timeout=2000;
		}
		else if(typeof timeout !="number"){
			timeout=2000;
		}
		setTimeout('$("#myModal").modal("hide")',timeout);
		
		if(typeof callback =='function'){
			modal.on('hidden',function(){
				callback();
			})
		}
	}

	$.fn.trimodal = function(options) {
		var settings=$.extend({},$.fn.trimodal.defaults,options);
		
		return this.each(function() {
			$(this).empty()
			var m = $(this);
			m.attr("class", "modal hide fade");
			m.attr('tabindex', '-1');
			m.attr('role', 'dialog');
			m.attr('aria-labelledby', 'myModalLabel');
			m.attr('aria-hidden', 'true');
			m
					.html('<div class="modal-header">'
							+ '<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>'
							+ '<h4 id="myLabel">'
							+ settings.modalLabel
							+ '</h4>'
							+ '</div>'
							+ '<div class="modal-body">'
							+ '<p>'
							+ settings.modalBody
							+ '</p>'
							+ '</div>'
							+ '<div class="modal-footer">'
							+ '<button class="btn btn-success">'+settings.btn_yes+'</button>'
							+ '<button class="btn btn-warning">'+settings.btn_no+'</button>'
							+ '<button class="btn btn-cancel" data-dismiss="modal" aria-hidden="true">'+settings.btn_cancel+'</button>'
							+ '</div>');
			$('.btn.btn-success').click(onSuccess);
			$('.btn.btn-warning').click(onWarning);
			$('.btn.btn-cancel').click(onCancel);
			if(typeof settings.callback =='function'){
				m.on('hidden',settings.callback);
			}
			m.modal({
				backdrop : true,
				keyboard : true
			});
			
		});
		
		function onSuccess(){
			var p=$(this).parent().parent();
			if(typeof settings.fun_yes=='function'){
				p.on('hidden',settings.fun_yes);
				p.on('hidden',null);
				p.modal('hide');
			}
			return false;
		}
		function onWarning(){
			var p=$(this).parent().parent();
			if(typeof settings.fun_no=='function'){
				p.on('hidden',settings.fun_no);
				p.on('hidden',null);
				p.modal('hide');
			}
			return false;
		}
		function onCancel(){
			var p=$(this).parent().parent();
			if(typeof settings.fun_cancel=='function'){
				p.on('hidden',settings.fun_cancel);
				p.on('hidden',null);
				p.modal('hide');
			}
			return false;
		}
	}

	$.fn.trimodal.defaults={
			modalLabel:'提示',
			modalBody:'',
			btn_yes:'是',
			btn_no:'否',
			btn_cancel:'取消',
			fun_yes:"",
			fun_no:"",
			fun_cancel:"",
			callback:""
	}
	
	
})(jQuery)