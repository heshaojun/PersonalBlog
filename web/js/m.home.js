//导航栏当前索引
var navigation_active_index = 0;
//文章列表排序方式
var article_list_order_type = "time";
//文章范围
var article_scope = "all";
//加载导航栏
$('.common_navigation_bar').load('_navigation-bar.html')
//加载右侧面板群
$('.common_right_panel').load('_right-panels.html');
//加载文章列表
$('.common_article_list').load('_article-list.html')

//文章列表排序方式函数
function article_list_sort(selector) {
    let glass = "text-primary";
    let data_mark = $(selector).attr("data-mark");
    let a_list = $(selector).parent().find('a');
    if ($(selector).attr("class").split(" ").indexOf(glass) >= 0) return;
    for (let i = 0; i < a_list.length; i++) {
        $(a_list[i]).removeClass(glass);
        if ($(a_list[i]).attr("data-mark") == data_mark) {
            $(a_list[i]).addClass(glass);
        }
    }
    article_list_order_type = data_mark;
    $('.common_article_list').load('_article-list.html')
}

//文章类型选择
function article_scope_option(selector) {
    let glass = "text-primary";
    let data_mark = $(selector).attr("data-mark");
    let a_list = $(selector).parent().find('a');
    if ($(selector).attr("class").split(" ").indexOf(glass) >= 0) return;
    for (let i = 0; i < a_list.length; i++) {
        $(a_list[i]).removeClass(glass);
        if ($(a_list[i]).attr("data-mark") == data_mark) {
            $(a_list[i]).addClass(glass);
        }
    }
    article_scope = data_mark;
    $('.common_article_list').load('_article-list.html')
}