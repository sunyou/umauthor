!function ($) {
	
	var SelectTree = function (element, options, cb) {
		//first node: treeId, treePid, treeName, isParent
		//url 
		//参数 ?
		//type ?
		//数据转换 ?
		//hide id ?
		
		var hasOptions = typeof options == 'object';
		
		this.parentEl = 'body';
		
		this.opens = 'right';
				
		this.parentEl = (hasOptions && options.parentEl && $(options.parentEl)) || $(this.parentEl);
		
		this.element = $(element);
		
		if (this.element.is('input')) {
            this.element.on({
                click: $.proxy(this.show, this),
                focus: $.proxy(this.show, this)
            });
        } else {
            this.element.on('click', $.proxy(this.show, this));
        }
		
		var eleId = this.element.attr('id');
		
		var DRPTemplate = '<div class="daterangepicker dropdown-menu">' +
	        '<div><ul id="'+eleId+'ZTREE" class="ztree"></ul></div>' +
	      '</div>';
		
		this.container = $(DRPTemplate).appendTo(this.parentEl);
		
		this.container.addClass('opens' + this.opens);
		
		this.container.on('mousedown', $.proxy(this.mousedown, this));
		
		
		var defaults = {
			type : 1, //单选
			eleId: eleId,
			hideId: eleId + 'TreeId',
			isParent: true,
			treePid:0,
			otherParam: {},
			autoParam:[],
			setDefault:false
		};
		options =  $.extend(true,{},defaults,options);
		
		//初始化树
		this.initTree(options);
	};
	
	SelectTree.prototype = {

	        constructor: SelectTree,
	        
	        initTree:function(op){
	        	
	        	if(op.setDefault){
	        		this.element.val(op.treeName);
		        	this.element.next().val(op.treeId);
	        	}
	        	
	        	var setting = {
	        			view: {
	        				selectedMulti: false
	        			},
	        			data:{
	        				dataTransform:function(dataArr){
	        					//console.log(dataArr);
	        					var newData = [];
	        					for(var i = 0;i< dataArr.length;i++){
	        						var isParent = true;
	        						var node = {
	        								id:dataArr[i].orgId,
	        								name:dataArr[i].orgName,
	        								pId:dataArr[i].parentOrgId,
	        								isParent:isParent
	        								
	        						};
	        						newData.push(node);
	        					}
	        					//console.log(newData);
	        					return newData;
	        				}
	        			},
	        			async: {
	        				enable: true,
	        				dataType: 'json',
	        				url:op.url,
	        				autoParam:op.autoParam,
	        				otherParam:op.otherParam
	        			},
	        			callback: {
	        				onClick: $.proxy(this.treeClick, this)
	        			}
	        		};
	        		
	        		
	        		var zNodes =[
	         			{ id:op.treeId, pId:op.treePid, name:op.treeName,isParent:op.isParent}
	         		];

	        	 	var selectTree = $.fn.zTree.init(this.container.find('.ztree'), setting, zNodes);
	        	 	var nodes = selectTree.getNodeByParam('id',op.treeId);
	        	 	selectTree.expandNode(nodes,true,false,false,false);  
	        },
	        refreshTree:function(op){
	        	//todo刷新树
	        },
	        setTreeValue:function(){
	        	//todo设置值
	        },
	        mousedown: function (e) {
	            e.stopPropagation();
	        },
	        
	        treeClick:function(event, treeId, treeNode, clickFlag){
	        	this.element.val(treeNode.name);
	        	this.element.next().val(treeNode.id);
	        	this.hide();
	        },
	        
	        move: function () {
	            var parentOffset = {
	                top: this.parentEl.offset().top - (this.parentEl.is('body') ? 0 : this.parentEl.scrollTop()),
	                left: this.parentEl.offset().left - (this.parentEl.is('body') ? 0 : this.parentEl.scrollLeft())
	            };
	            if (this.opens == 'left') {
	                this.container.css({
	                    top: this.element.offset().top + this.element.outerHeight() - parentOffset.top,
	                    right: $(window).width() - this.element.offset().left - this.element.outerWidth() - parentOffset.left,
	                    left: 'auto'
	                });
	                if (this.container.offset().left < 0) {
	                    this.container.css({
	                        right: 'auto',
	                        left: 9
	                    });
	                }
	            } else {
	                this.container.css({
	                    top: this.element.offset().top + this.element.outerHeight() - parentOffset.top,
	                    left: this.element.offset().left - parentOffset.left,
	                    right: 'auto'
	                });
	                if (this.container.offset().left + this.container.outerWidth() > $(window).width()) {
	                    this.container.css({
	                        left: 'auto',
	                        right: 0
	                    });
	                }
	            }
	        },
	        
	        show: function (e) {
	            this.container.show();
	            this.move();

	            if (e) {
	                e.stopPropagation();
	                e.preventDefault();
	            }

	            $(document).on('mousedown', $.proxy(this.hide, this));
	            this.element.trigger('shown', {target: e.target, picker: this});
	        },
	        
	        hide: function (e) {
	            this.container.hide();

//	            if (!this.startDate.isSame(this.oldStartDate) || !this.endDate.isSame(this.oldEndDate))
//	                this.notify();
//
//	            this.oldStartDate = this.startDate.clone();
//	            this.oldEndDate = this.endDate.clone();

	            $(document).off('mousedown', this.hide);
	            this.element.trigger('hidden', { picker: this });
	        },
	        destory:function(){
	        	this.container.remove();
	        }
	};
	
	$.fn.selecttree = function (options, cb) {
		options = options || {};
        this.each(function () {
            var el = $(this);
            if (!el.data('selecttree')){
        		el.data('selecttree', new SelectTree(el, options, cb));
        	}else{
        		if(options.refresh){
        			el.data('selecttree').destory();
        			el.data('selecttree', new SelectTree(el, options, cb));
        		}
        	}
            
        });
        return this;
    };

}(window.jQuery);