'use strict'
 $.widget( "custom.catcomplete", $.ui.autocomplete, {
    _renderMenu: function( ul, items ) {
      var that = this,
        currentCategory = "";
      $.each( items, function( index, item ) {
        if ( item.category != currentCategory ) {
          ul.append( "<li class='ui-autocomplete-category'>" + item.category + "</li>" );
          currentCategory = item.category;
        }
        that._renderItemData( ul, item );
      });
    }
  });

var tabAllCleartimeout;
$(function(){
   if (document.all && !window.setTimeout.isPolyfill) {
  var __nativeST__ = window.setTimeout;
    window.setTimeout = function (vCallback, nDelay /*, argumentToPass1, argumentToPass2, etc. */) {
    var aArgs = Array.prototype.slice.call(arguments, 2);
    return __nativeST__(vCallback instanceof Function ? function () {
      vCallback.apply(null, aArgs);
    } : vCallback, nDelay);
  };
   window.setTimeout.isPolyfill = true;
  }

   if (document.all && !window.setInterval.isPolyfill) {
  var __nativeSI__ = window.setInterval;
  window.setInterval = function (vCallback, nDelay /*, argumentToPass1, argumentToPass2, etc. */) {
    var aArgs = Array.prototype.slice.call(arguments, 2);
    return __nativeSI__(vCallback instanceof Function ? function () {
      vCallback.apply(null, aArgs);
    } : vCallback, nDelay);
  };
  window.setInterval.isPolyfill = true;
 }

        $('#S_ICOSearch').catcomplete({
            delay: 700,
            source: function(request, response) {
                $.ajax({
                    url: "/getCoins",
                    dataType: "text",
                    data: {
                        coins: request.term
                    },
                    success: function( data ) {
                        response( $.map( JSON.parse(data), function( item ) {
                            return {
                                label: item.label.split('(')[0],
                                category:item.category
                            }
                        }));
                    }
                });
            },
            select:function(event, item){
                let label=item.item.label.split('(')[0];
                $('#S_ICOSearch').val(label);
                AjaxGetData('/getTokenDetail',{token:label,page:-1},'get',function (data) {
                    ICORowData(data);
                },function (err) {
                    console.log(err);
                });
                return false;
            }
        });
   $('#S_but_Search').click(function () {
       if($('#S_ICOSearch').val()!=''){
           AjaxGetData('/getTokenDetail',{token:$('#S_ICOSearch').val(),page:-1},'get',function (data) {
               ICORowData(data);
           },function (err) {
               console.log(err);
           });
       }
   });
   var tokenfunc=function () {
       AjaxGetData('/getTokenCNY',{},'get',function (data) {
           let coins=$('.S_H_ps_Con');
           data=JSON.parse(data);
           $(coins[0]).text('￥ '+data.btc);
           $(coins[1]).text('￥ '+data.eth);
           $(coins[2]).text('￥ '+data.eos);
       },function (err) {
         //  showBox("操作提示", "获取token信息失败!", 'warning', true);
           console.log(err);
       });};
       tokenfunc();
   var tokenCNY=setInterval(tokenfunc,60000);


    //更多选项下 下拉框操作
    $('#tabAll a').hover(function(){
     if(tabAllCleartimeout!=undefined){
          clearTimeout(tabAllCleartimeout);
      }
       $(this).addClass('S_l_a_bt').siblings().removeClass('S_l_a_bt');
       $(this).children('i').removeClass('glyphicon-plus').addClass("glyphicon-minus").parent().siblings().children('i').removeClass('glyphicon-minus').addClass("glyphicon-plus"); 
        $('#'+this.dataset.id).css('display','block').siblings().css('display','none');
    },(e)=>{
      if (e.toElement!=null&&e.toElement.dataset.parentid==e.currentTarget.id) return;
      if(tabAllCleartimeout!=undefined){
          clearTimeout(tabAllCleartimeout);
      }
      tabAllCleartimeout=setTimeout(tabAllmleave,1000,e.currentTarget.id,e.currentTarget.dataset.id);
    });
    //更多选项下 下拉框操作显示后触发hover
    $('#tabAllChild div').hover(()=>{},function(e){
        if(e.toElement!=null&&e.toElement.dataset.id!=undefined){
      if(tabAllCleartimeout!=undefined){
          clearTimeout(tabAllCleartimeout);
      }}else{
      tabAllCleartimeout=setTimeout(tabAllmleave,1000,e.currentTarget.dataset.parentid,e.currentTarget.id);
      }    
     });
    //更多选项下 下拉框操作显示后鼠标离开
    function tabAllmleave(mleaveid,mleavechildid){
       $('#'+mleaveid).removeClass('S_l_a_bt').children('i').removeClass('glyphicon-minus').addClass("glyphicon-plus"); ;
          $('#'+mleavechildid).css('display','none');
    }
     //投资机构 更多操作
    $('#investmentul_g_s1').click(function(){
      if($(this).text()=="更多"){
        $(this).html('收取<i class="glyphicon glyphicon-minus"></i>');
        $('#S_gfc').addClass('ahover').siblings().removeClass('ahover');
        $('#S_show_g').css('display','block');
        $('#investmentul_g').addClass('S_ig_scroll');
      }else{
        $(this).html('更多<i class="glyphicon glyphicon-plus"></i>');
        $('#S_show_g').css('display','none').children('a').removeClass('ahover');
        var tmpli=$('#investmentul_g .investmentul').children('li');
       for (var index = 0; index < tmpli.length; index++) {
         var element = tmpli[index];
         if(element.dataset.top=='Y'){
           $(element).css('display','block');
         }else{
            $(element).css('display','none');
         }
       }
        $('#investmentul_g').removeClass('S_ig_scroll'); 
     }
    })
    //投资机构 通过字母搜索
    $('#S_show_g a').mouseenter(function(){
       $(this).addClass('ahover').parent().siblings().children('a').removeClass('ahover');
       var showFlag=this.dataset.sh;
       if(showFlag!='0'){
       var tmpli=$('#investmentul_g .investmentul').children('li');
       for (var index = 0; index < tmpli.length; index++) {
         var element = tmpli[index];
         if(element.dataset.sh==showFlag){
           $(element).css('display','block');
         }else{
            $(element).css('display','none');
         }
       }
      }else{
        $('#investmentul_g .investmentul').children('li').css('display','block');
      }
    })
    //绑定查询条件点击事件 并在已选条件显示
   $('#investmentul_g .investmentul').children(' li').click(function(e){
     SWrapClick_d(e);
    });
     $('#countryT').children('ul').children('li').click(function(e){
     SWrapClick(e);
    });
    $('#trendT').children('ul').children('li').click(function(e){
     SWrapClick(e);
    });
    $('#ROI_g').children('li').click(function(e){
     SWrapClick(e);
    });
    $('#ROI_b').children('li').click(function(e){
     SWrapClick(e);
    });
    $('#market').children('li').click(function(e){
     SWrapClick(e);
    });
     $('#genre').children('li').click(function(e){
     SWrapClick(e);
    });  
    //投资机构对应点击方法 最多在已选条件下点击5个     
   function SWrapClick_d(e){
      if(e.currentTarget.dataset!=null&&e.currentTarget.dataset.type!=undefined){
    
      var s='<div data-type="'+e.currentTarget.dataset.type+'" data-value="'+e.currentTarget.dataset.value+'" class="cond cond_s">'+e.currentTarget.dataset.name+'<div class="Closecond" onclick="removecond(this)"><i  class="glyphicon glyphicon-remove"></i></div></div>';
      var div_c=$('#cond').children('div');
      for (let index = 0; index < div_c.length; index++) {
        let element = div_c[index];
        if(element.dataset.type==e.currentTarget.dataset.type&&e.currentTarget.dataset.value==element.dataset.value){
          $(element).remove();
        }
      }
      var flag=0;
       div_c=$('#cond').children('div');
        for (let index = 0; index < div_c.length; index++) {
        let element = div_c[index];
        if(element.dataset.type=='0'){
         flag++;
        }
      }
       if(flag<5){
       $('#cond').append(s);
      }else{
          showBox("操作提示", "投资机构一次最多选5个！", 'warning', true); 
      }
      }
   } 
  // ICORowData();

   //查询条件其它点击事件对应方法
    function SWrapClick(e){
            if(e.currentTarget.dataset!=null&&e.currentTarget.dataset.type!=undefined){
    
      var s='<div data-type="'+e.currentTarget.dataset.type+'" data-value="'+e.currentTarget.dataset.value+'" class="cond cond_s">'+e.currentTarget.dataset.name+'<div class="Closecond" onclick="removecond(this)"> <i  class="glyphicon glyphicon-remove"></i></div></div>';
      var div_c=$('#cond').children('div');
      for (let index = 0; index < div_c.length; index++) {
        let element = div_c[index];
        if(element.dataset.type==e.currentTarget.dataset.type){
          $(element).remove();
        }
      }
       $('#cond').append(s);
      }
    }
  $('#marketbtn').click(function(){

    SetCondDate('2','4','markettext1','markettext2');
  });
  $('#ROIbtn').click(function(){
   SetCondDate('3','7','ROItext1','ROItext2');
  });
    //清除高级搜索所有条件
  $('.removeCondT').click(function(){
    $('#cond').html('');
  });
  //通过高级搜索做查询
    $('.searchCondT').click(function(){
       $('#ContrastDetailTab').css('display','none');
       let conddiv=$('#cond').children('div');
       let strCond='';
       if(conddiv.length>0){
        for (let i = 0; i < conddiv.length; i++) {
            if(i==conddiv.length-1){
                strCond+=conddiv[i].dataset.type+'|'+conddiv[i].dataset.value;
            }else{
                strCond+=conddiv[i].dataset.type+'|'+conddiv[i].dataset.value+';';
            }
        }
        AjaxGetData('/getCoinsDetail',{token:strCond,page:-1},'get',function (data) {
               ICORowData(data);
           },function (err) {
               console.log(err);
           });

       }
    });
  //循环加入查询清单
  function ICORowData(icorowData){
    $('#ContrastDetailTab').css('display','none');
    var ICORowbut=$('.ICORowbut');
    if(ICORowbut.length>0){
        ICORowbut.unbind();
    }
      icorowData=JSON.parse(icorowData);
    let tatolCount=icorowData.totalCount;
    let icorowdata=icorowData.token;
    $('.S_content').css('visibility','hidden');
    let icorowContent='';
   /*  icorowdata=[{a1:'~/../image/102.jpeg',a2:'EOS1',a3:'eos1',b1:'47.51',b2:'5.4',b3:'53亿',b4:'7',b5:'2017-07-02'
      ,c1:'https://eos.io/',c2:'EOS',c3:' IO is software that introduces a blockchain architecture designed to enable vertical....'
      ,c4:'425亿',c5:'主网上线',c6:'智能合约',c7:'5653(+7)',c8:'13952/+231',c9:'422/2.4%'
      ,d1:'220,206,197,197,196,211,211,207,209,209,208,210,211,222,222,226,228,242,239,240,240,246,244,244,243,234,234,227'
     }
      ,{a1:'~/../image/102.jpeg',a2:'EOS2',a3:'eos2',b1:'47.51',b2:'5.4',b3:'53亿',b4:'7',b5:'2017-07-02'
      ,c1:'https://eos.io/',c2:'EOS',c3:' IO is software that introduces a blockchain architecture designed to enable vertical....'
      ,c4:'425亿',c5:'主网上线',c6:'智能合约',c7:'5653(+7)',c8:'13952/+231',c9:'422/2.4%'
      ,d1:'220,206,197,197,196,211,211,207,209,209,208,210,211,222,222,226,228,242,239,240,240,246,244,244,243,234,234,227'}
      ,{a1:'~/../image/102.jpeg',a2:'EOS3',a3:'eos3',b1:'47.51',b2:'5.4',b3:'53亿',b4:'7',b5:'2017-07-02'
      ,c1:'https://eos.io/',c2:'EOS',c3:' IO is software that introduces a blockchain architecture designed to enable vertical....'
      ,c4:'425亿',c5:'主网上线',c6:'智能合约',c7:'5653(+7)',c8:'13952/+231',c9:'422/2.4%'
     ,d1:'220,206,197,197,196,211,211,207,209,209,208,210,211,222,222,226,228,242,239,240,240,246,244,244,243,234,234,227'}
      ,{a1:'~/../image/102.jpeg',a2:'EOS4',a3:'eos4',b1:'47.51',b2:'5.4',b3:'53亿',b4:'7',b5:'2017-07-02'
      ,c1:'https://eos.io/',c2:'EOS',c3:' IO is software that introduces a blockchain architecture designed to enable vertical....'
      ,c4:'425亿',c5:'主网上线',c6:'智能合约',c7:'5653(+7)',c8:'13952/+231',c9:'422/2.4%'
     ,d1:'220,206,197,197,196,211,211,207,209,209,208,210,211,222,222,226,228,242,239,240,240,246,244,244,243,234,234,227'}
      ,{a1:'~/../image/102.jpeg',a2:'EOS5',a3:'eos5',b1:'47.51',b2:'5.4',b3:'53亿',b4:'7',b5:'2017-07-02'
      ,c1:'https://eos.io/',c2:'EOS',c3:' IO is software that introduces a blockchain architecture designed to enable vertical....'
      ,c4:'425亿',c5:'主网上线',c6:'智能合约',c7:'5653(+7)',c8:'13952/+231',c9:'422/2.4%'
      ,d1:'220,206,197,197,196,211,211,207,209,209,208,210,211,222,222,226,228,242,239,240,240,246,244,244,243,234,234,227'}
      ,{a1:'~/../image/102.jpeg',a2:'EOS6',a3:'eos6',b1:'47.51',b2:'5.4',b3:'53亿',b4:'7',b5:'2017-07-02'
      ,c1:'https://eos.io/',c2:'EOS',c3:' IO is software that introduces a blockchain architecture designed to enable vertical....'
      ,c4:'425亿',c5:'主网上线',c6:'智能合约',c7:'5653(+7)',c8:'13952/+231',c9:'422/2.4%'
      ,d1:'220,206,197,197,196,211,211,207,209,209,208,210,211,222,222,226,228,242,239,240,240,246,244,244,243,234,234,227'}
      ,{a1:'~/../image/102.jpeg',a2:'EOS',a3:'eos',b1:'47.51',b2:'5.4',b3:'53亿',b4:'7',b5:'2017-07-02'
      ,c1:'https://eos.io/',c2:'EOS',c3:' IO is software that introduces a blockchain architecture designed to enable vertical....'
      ,c4:'425亿',c5:'主网上线',c6:'智能合约',c7:'5653(+7)',c8:'13952/+231',c9:'422/2.4%'
      ,d1:'220,206,197,197,196,211,211,207,209,209,208,210,211,222,222,226,228,242,239,240,240,246,244,244,243,234,234,227'}
      ,{a1:'~/../image/102.jpeg',a2:'EOS7',a3:'eos7',b1:'47.51',b2:'5.4',b3:'53亿',b4:'7',b5:'2017-07-02'
      ,c1:'https://eos.io/',c2:'EOS',c3:' IO is software that introduces a blockchain architecture designed to enable vertical....'
      ,c4:'425亿',c5:'主网上线',c6:'智能合约',c7:'5653(+7)',c8:'13952/+231',c9:'422/2.4%'
      ,d1:'220,206,197,197,196,211,211,207,209,209,208,210,211,222,222,226,228,242,239,240,240,246,244,244,243,234,234,227'}
      ,{a1:'~/../image/102.jpeg',a2:'EOS8',a3:'eos8',b1:'47.51',b2:'5.4',b3:'53亿',b4:'7',b5:'2017-07-02'
      ,c1:'https://eos.io/',c2:'EOS',c3:' IO is software that introduces a blockchain architecture designed to enable vertical....'
      ,c4:'425亿',c5:'主网上线',c6:'智能合约',c7:'5653(+7)',c8:'13952/+231',c9:'422/2.4%'
     ,d1:'220,206,197,197,196,211,211,207,209,209,208,210,211,222,222,226,228,242,239,240,240,246,244,244,243,234,234,227'}
      ,{a1:'~/../image/102.jpeg',a2:'EOS9',a3:'eos9',b1:'47.51',b2:'5.4',b3:'53亿',b4:'7',b5:'2017-07-02'
      ,c1:'https://eos.io/',c2:'EOS',c3:' IO is software that introduces a blockchain architecture designed to enable vertical....'
      ,c4:'425亿',c5:'主网上线',c6:'智能合约',c7:'5653(+7)',c8:'13952/+231',c9:'422/2.4%'
     ,d1:'220,206,197,197,196,211,211,207,209,209,208,210,211,222,222,226,228,242,239,240,240,246,244,244,243,234,234,227'}];
*/

   
    for (var index = 0; index < icorowdata.length; index++) {
      var element = icorowdata[index];
      let icorowdiv='\n<div class="ICORow">\
          <div class="ICORowimg">\
            <img width="70px" height="70px" src="{0}" alt="{1}"/>\
            <strong style="line-height: 30px;font-size: 16px;">{1}</strong>\
          </div>\
          <div class="ICORowdetail" >\
              <div style="width:150px;">\
                <div style="display: flex;"><strong class="ICORD_name">￥{3}</strong><div style="padding-top: 8px;\
            padding-left: 3px;color: #009966;font-weight: 700;">{4}%</div></div>\
                <div style="padding-left:3px;">日成交额：¥{5}</div>\
                <div style="padding-left:3px;">众筹价格：￥{6}</div>\
                <div style="padding-left:3px;">发行时间：{7}</div>\
              </div>\
              <div style="width: 498px;overflow:hidden;">\
                    <div>\
                     <div class="ICORD_desc"><span><a  href="{8}" target="_blank">{9}</a>{10}</span></div>  \
                    </div>\
                    <div class="ICOExplain">\
                      <div>\
                        <div>流通市值：¥{11}</div>\
                        <div>项目进度：{12}</div>\
                        <div>项目类型：{13}</div>\
                      </div>\
                     <div class="ICORD_udesc">\
                          <div><div class="ICORD_udescJ">推特粉丝(7d)</div>{14}</div>\
                          <div><div class="ICORD_udescJ">电报成员(7d)</div>{15}</div>\
                          <div><div class="ICORD_udescJ">活跃用户(24h)</div>{16}%</div>\
                     </div>\
                   </div>\
              </div>\
          </div>\
          <div class="ICORowchart">\
             <div style="padding-top:6px;">\
                  <span style="display:none;" data-peity="{fill: none}" class="line">{17}</span>\
             </div>\
             <div style="float:left;margin-left:20px;padding-top:5px;">价格趋势(7天)</div>\
             <div style="float:left;margin-left:10px;"><div data-img="{0}" data-tokenfull="{2}" data-token="{1}" class="ICORowbut">加入对比<i class="glyphicon glyphicon-heart"></i></div></div>\
          </div>\
       </div>';
      icorowContent+=icorowdiv.format(element.a1,element.a2,element.a3,element.b1,element.b2,element.b3,element.b4,element.b5,
        element.c1,element.c2,element.c3,element.c4,element.c5,element.c6,element.c7,element.c8,element.c9
        ,element.d1);
    }
     $("#ICORowDetail").html(icorowContent);
      $(".line").peity("line",{fill:'none',height:'58px', width:'100%'});
     $('.S_content').css('visibility','visible');
     //加入对比清单 绑定清除方法
     $('.ICORowbut').click(function(){
      if(this.dataset!=null){
           var ICOCRowI=$('.ICOCRowI');
           if(ICOCRowI.length<5){
             if(ICOCRowI.length>0){
              for (let j = 0; j < ICOCRowI.length; j++) {
                  let ele = ICOCRowI[j];
                  if(this.dataset.tokenfull==ele.dataset.tokenfull){
                    $(ele).parent().remove();
                    }
                   }
                  ICOCRowI.unbind();
                 }
                  let ICOContract='<div  class="ICOContrastRow">\
               <div><img width="70px" height="70px" src="{0}" alt="{1}"/></div>\
               <div>\
                  <div class="ICOCRowTit">{1}</div>\
                  <div class="ICOCRowTittext">{2}</div>\
               </div>\
               <div data-tokenfull={2} class="ICOCRowI"><i  class="glyphicon glyphicon-remove-sign"></i></div>\
               </div>';
               $('.ICOCWrap').append(ICOContract.format(this.dataset.img,this.dataset.token,this.dataset.tokenfull));
               $('.ICOCRowI').click(function(){
                $(this).parent().remove();
              });
            }else{
             showBox("操作提示", "一次最多添加5个!", 'warning', true);
            }
       } 
     })
  }
  //清除对比框所有条件
  $('#ICOCRemoveAll').click(function(){
    $('.ICOCWrap').html('');
  });
  //开始对比
  $('#ICOContrast').click(function () {
      $('#searchdis').css('display','none');
      $('#ContrastDetailTab').css('display','block');
       GetEcharts();
       GetTable();
  });
  //打开对比界面的已选条件对应的下拉框
    $('#cronbtnOC').hover(function(){
     if(tabAllCleartimeout!=undefined){
          clearTimeout(tabAllCleartimeout);
      }
       $(this).addClass('S_l_a_bt');
       $('.crontopdivShowTit').css('display','block');
    },(e)=>{
      if (e.toElement!=null&&e.toElement.dataset.id=="crontopdivShow") return;
      if(tabAllCleartimeout!=undefined){
          clearTimeout(tabAllCleartimeout);
      }
      tabAllCleartimeout=setTimeout(function(){
         $('.crontopdivShowTit').css('display','none');
          $('#cronbtnOC').removeClass('S_l_a_bt');
      },1000);
    })
    //打开对比界面的已选条件对应的下拉框 进入下拉框后无操作 离开注册定时器
    $('.crontopdivShowTit').hover(()=>{},function(e){
        if(e.toElement!=null&&e.toElement.dataset.id=='cronbtnOC'){
           if(tabAllCleartimeout!=undefined){
          clearTimeout(tabAllCleartimeout);
          }}else{
             tabAllCleartimeout=setTimeout(function(){
               $('.crontopdivShowTit').css('display','none');
               $('#cronbtnOC').removeClass('S_l_a_bt');
             },1000);
          }    
     }
    )
    //移除所有加入对比队列的元素
    $('#cronbtnremove').click(function(){
        $('#crontopdivShowul').html('');
    })
    //单个加入对比框元素
    $('#cronbtn').click(function(){
        let crontext=$('#crontext');
    //    if(crontext.data('id')=='0'){
    //      showBox("操作提示", "请输入正确的代币信息！", 'warning', true); 
    //    }
        if(crontext.val().trim()!=''){
            
           $('#crontopdivShowul').append(
               '<li><a href="javascript:;">'+crontext.val().trim()+'<i  class="removelii glyphicon glyphicon-remove"></i></a></li>');
          $('#crontopdivShowul').children('li').children('a').children('i').off("click").click(function(){
               $(this).parent().parent().remove();
           });
        }else{
          showBox("操作提示", "请输入正确的代币信息！", 'warning', true);    
        }
    })
   //  GetEcharts();
    // GetTable();

})
//   
 //高级搜索确定按钮对应事件 参数大类型 小类型 对应id1和2
 function SetCondDate(flag,flg,id1,id2){
     let id1val=$('#'+id1).val().trim();
     let id2val=$('#'+id2).val().trim();
    if(id1val.length==0){
       showBox("操作提示", "文本不能为空,且为数字。", 'warning', true);
       $('#'+id1).focus();
       return;
    }else{
        let patt1=new RegExp(/^[0-9]+$/);
        if(!patt1.test(id1val)){
            showBox("操作提示", "请输入正确的数字!", 'warning', true);
            $('#'+id1).focus();
            return;
        }
    }
     if(id2val.length==0){
       showBox("操作提示", "文本不能为空,且为数字。", 'warning', true);
       $('#'+id2).focus();
       return;
    }else{
        let patt2=new RegExp(/^[0-9]+$/);
        if(!patt2.test(id2val)){
            showBox("操作提示", "请输入正确的数字!", 'warning', true);
            $('#'+id2).focus();
            return;
        }else {
            if(Number(id2val)<=Number(id1val)){
                showBox("操作提示", "请输入合理的区间范围!", 'warning', true);
                return;
            }
        }
    }
    var s='<div data-type="'+flag+'" data-value="'+flg+'" class="cond cond_s">'+id1val+'~'+id2val+'<div class="Closecond" onclick="removecond(this)"> <i  class="glyphicon glyphicon-remove"></i></div></div>';
      var div_c=$('#cond').children('div');
      for (let index = 0; index < div_c.length; index++) {
        let element = div_c[index];
        if(element.dataset.type==flag){
          $(element).remove();
        }
      }
       $('#cond').append(s);
 }

 function removecond(e){
      $(e).parent().remove();
    }
//  function GetData(url,params,Callback,failcallback){
//    params.random=Math.random();
//    $.ajax({
//         url:url,
//         data:params,
//         datatype:'json'

//    }).done().fail();
//  }
 function AjaxGetData(url, params,type, callback, failCallBack) {
    params.random = Math.random();
    $.ajax({
        url: url,
        type: type,
        data: params,
        dataType: "text",
        async: true
    }).done(function (data) {
        callback(data);
    }).fail(function (err) {
        failCallBack(err);
       // console.log(err);
        //error success  warning

    });
 }

 function GetEcharts(){
   // $('#searchdis').css('display','none');
   $('#ContrastDetail').css('display','block');
   var mycharts = echarts.init(document.getElementById('S_charts'));
  var option = {
       title: {
        text: '折线图堆叠'
       },
    backgroundColor: '#fff',
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:['邮件营销','联盟广告','视频广告','直接访问','搜索引擎']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
     dataZoom: [
        {
             type: 'inside',
            show: true,
            realtime: true,
            start: 65,
            end: 85
        }
    ],
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['周一','周二','周三','周四','周五','周六','周日']
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name:'邮件营销',
            type:'line',
            stack: '总量',
            data:[120, 132, 101, 134, 90, 230, 210]
        },
        {
            name:'联盟广告',
            type:'line',
            stack: '总量',
            data:[220, 182, 191, 234, 290, 330, 310]
        },
        {
            name:'视频广告',
            type:'line',
            stack: '总量',
            data:[150, 232, 201, 154, 190, 330, 410]
        },
        {
            name:'直接访问',
            type:'line',
            stack: '总量',
            data:[320, 332, 301, 334, 390, 330, 320]
        },
        {
            name:'搜索引擎',
            type:'line',
            stack: '总量',
            data:[820, 932, 901, 934, 1290, 1330, 1320]
        }
    ]
  };
  mycharts.setOption(option);
 }

 function GetTable(){

   let tit=['telegram用户数','telegram留言数','telegram活跃用户','telegram用户新增数','telegram活跃用户占比','推特用户数','推特点赞数','推特用户新增数','网站搜索排名','众筹单价-USD','众筹单价-BTC','众筹单价-ETH','代币总量','众筹代币占比','众筹硬顶','众筹完成比例','众筹开始时间','众筹完成时间','测试网络/测试版DAPP是否上线','测试网络/测试版DAPP上线时间','主链/正式版DAPP是否上线','主链/正式版DAPP上线时间','code贡献人数','code问题讨论数','code问题解决数','code改动量','code更新频率','fork次数','star次数','watch次数','核心开发人员人数','核心开发人员1参与项目数','核心开发人员1参与项目被关注数','核心开发人员2参与项目数','核心开发人员2参与项目被关注数','核心开发人员3参与项目数','核心开发人员3参与项目被关注数','代码独创性','投资机构数量','顶级投资机构数量','投资名单','战略合作机构数量','独角兽合作机构数量','合作机构名单','coindesk报道次数','PR次数','推特每周发布数'];
   let tit1=['众筹价-USD','众筹回报率-USD','众筹回报率-BTC','众筹回报率-ETH','换手率','流通率','流通数量','流通市值','流通市值排名','日变化幅度','周变化幅度','月变化幅度','日成交额','token持仓TOP200占比','token持仓TOP100占比','活跃钱包数','交易所数量','交易所清单'];
   var titData=[],tit1Data=[];
   for (let index = 0; index < tit.length; index++) {
    let element = tit[index];
     titData[titData.length]={id:element,name1:1+Math.round(Math.random()*100),name2:2+Math.round(Math.random()*100)
         ,name3:3+Math.round(Math.random()*100),name4:4+Math.round(Math.random()*100)
         ,name5:5+Math.round(Math.random()*100)}
   }
   for (let index = 0; index < tit1.length; index++) {
    let element = tit1[index];
     tit1Data[tit1Data.length]={id:element,name1:1+Math.round(Math.random()*100),name2:2+Math.round(Math.random()*100)
         ,name3:3+Math.round(Math.random()*100),name4:4+Math.round(Math.random()*100)
         ,name5:5+Math.round(Math.random()*100)}
    }
   $('#table').bootstrapTable({
    columns: [{
        field: 'id',
        title: '基本面',
        class:'tablecol1'
    }, {
        field: 'name1',
        title: '项目一'
    }, {
        field: 'name2',
        title: '项目二'
    }, {
        field: 'name3',
        title: '项目三'
    }, {
        field: 'name4',
        title: '项目四'
    }, {
        field: 'name5',
        title: '项目五'
    }],
    data: titData
  });
  $('#table1').bootstrapTable({
        columns: [{
        field: 'id',
        title: '市场面',
        class:'tablecol1'
    }, {
        field: 'name1',
        title: '项目一'
    }, {
        field: 'name2',
        title: '项目二'
    }, {
        field: 'name3',
        title: '项目三'
    }, {
        field: 'name4',
        title: '项目四'
    }, {
        field: 'name5',
        title: '项目五'
    }],
    data: tit1Data
  });
 }
 /**
 * @param title   弹框消息的标题
 * @param text    弹框消息的内容
 * @param type     弹框消息的类型 success,info,warning,error
 * @param positionClass 进度条是否显示
 *///弹出窗的位置 toast-top-right,toast-bottom-right,toast-bottom-left,toast-top-left,toast-top-full-width,
// toast-bottom-full-width,toast-top-center,toast-bottom-center
function showBox(title, text, type, positionClass, position) {
    toastr.clear();
    //参数设置，若用默认值可以省略以下面代
    toastr.options = {
        "closeButton": true, //是否显示关闭按钮
        "debug": false, //是否使用debug模式
        "progressBar": true,//是否显示进度条
        "positionClass": position ? position : "toast-top-right",//弹出窗的位置
        "showDuration": "300",//显示动作（从无到有这个动作）持续的时间
        "hideDuration": "1000",//消失的动画时间
        "timeOut": "3000", //展现时间
        "extendedTimeOut": "1000",//加长展示时间
        "showEasing": "swing",//显示时的动画缓冲方式
        "hideEasing": "linear",//消失时的动画缓冲方式
        "showMethod": "show",//显示时的动画方式  fadeIn+jQuery的展示效果
        "hideMethod": "hide" //消失时的动画方式fadeout+jQuery的消失效果
    };
    toastr[type ? type : 'error'](text, title);
};

 String.prototype.format = function(args) {
            var result = this;
            if (arguments.length > 0) {
                if (arguments.length == 1 && typeof (args) == "object") {
                    for (var key in args) {
                        if (args[key] != undefined) {
                            var reg = new RegExp("({" + key + "})", "g");
                            result = result.replace(reg, args[key]);
                        }
                    }
                }
                else {
                    for (var i = 0; i < arguments.length; i++) {
                        if (arguments[i] != undefined) {
                            //var reg = new RegExp("({[" + i + "]})", "g");//这个在索引大于9时会有问题，谢谢何以笙箫的指出
                            var reg = new RegExp("({)" + i + "(})", "g");
                            result = result.replace(reg, arguments[i]);
                        }
                    }
                }
            }
            return result;
        }
        String.format = function () {
            if (arguments.length == 0)
                return null;

            var str = arguments[0];
            for (var i = 1; i < arguments.length; i++) {
                var re = new RegExp('\\{' + (i - 1) + '\\}', 'gm');
                str = str.replace(re, arguments[i]);
            }
            return str;
        } 