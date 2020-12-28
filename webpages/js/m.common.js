//全局变量
let mainNavBtnIndex = 1;
//主页文章列表范围，全局状态变量
let articleScope = 'all'; // all  blog  note
//主页文章列表排列方式
let articleOrderBy = 'time'; //time visit
//分类标识
let classifyMark = 'none';//clab_tests  分类标签  alab_cxx  文章标签 kw_ttt
//滚动栏优化
$(function () {
    $(".sticky-top").find("div").bind("DOMSubtreeModified", function () {
        let parent = $(this).parent(".sticky-top");
        parent.css("top", $(window).height() - parent.height());
    });
});


showdown.setFlavor('github');
let converter = new showdown.Converter({noHeaderId: true});


//加载文章列表
function load_article_list(selector, data) {
    let html = "<div class='border-0'>";
    let list = data['list'];
    for (let i = 0; i < list.length; i++) {
        let item = list[i];
        let div = ' <a class="list-group-item list-group-item-action pt-1 pb-1  border-bottom" href="/detail/' + item['id'] + '">\n' +
            '        <div class="w-100"><span class="small pl-1 pr-1 bg-warning text-info">' + item['type'] + '</span><span\n' +
            '                class=" font-weight-bold ml-2">' + item['title'] + '</span></div>\n';
        if (item['summary']) {
            div += '        <div><span>' + item['summary'] + '</span></div>\n';
            div += '        <div>\n' +
                '            <span class="small">' + item['createTime'] + '</span>\n' +
                '            <span class="ml-3 small"><img class="mr-1" src="icon/watch_icon.png" height="20px">' + item['visits'] + '</span>\n' +
                '            <span class="ml-3 small"><img class="mr-1" src="icon/comments_icon.png" height="20px">' + item['comments'] + '</span>\n' +
                '        </div>\n'
        }
        div += '    </a>';
        html += div;
    }
    html += "</div>";
    $(selector).html(html);
}

//加分页栏
function loadPagination(selector, current, total, callback) {
    let html = '<ul class="pagination justify-content-center mb-0">\n';
    if (current == 1) {
        html += '<li class="page-item disabled">\n' +
            '            <a class="page-link disabled"  page-index="pre" aria-disabled="true">上一页</a>\n' +
            '        </li>\n';
    } else {
        html += '<li class="page-item">\n' +
            '            <a class="page-link"  page-index="pre">上一页</a>\n' +
            '        </li>\n';
    }
    if (total <= 7) {
        for (let i = 1; i <= total; i++) {
            if (i == current) {
                html += '        <li class="page-item active"><a class="page-link disabled" page-index="' + i + '" >' + i + '</a></li>\n';
                continue;
            }
            html += '        <li class="page-item"><a class="page-link" page-index="' + i + '" >' + i + '</a></li>\n';
        }
    } else {
        if (current - 1 > 3) {
            html += '        <li class="page-item "><a class="page-link" page-index="1" >1</a></li>\n';
            html += '        <li class="page-item disabled"><a class="page-link disabled" page-index="-1" >...</a></li>\n';
            if (total - current > 3) {
                html += '        <li class="page-item "><a class="page-link" page-index="' + (current - 1) + '" >' + (current - 1) + '</a></li>\n';
                html += '        <li class="page-item active"><a class="page-link disabled" page-index="' + current + '" >' + current + '</a></li>\n';
                html += '        <li class="page-item "><a class="page-link" page-index="' + (current - 1) + '" >' + (current - 1) + '</a></li>\n';
                html += '        <li class="page-item disabled"><a class="page-link disabled" page-index="-1" >...</a></li>\n';
                html += '        <li class="page-item active"><a class="page-link disabled" page-index="' + total + '" >' + total + '</a></li>\n';
            } else {
                for (let i = total - 4; i <= total; i++) {
                    if (i == current) {
                        html += '        <li class="page-item active"><a class="page-link disabled" page-index="' + i + '" >' + i + '</a></li>\n';
                    } else {
                        html += '        <li class="page-item"><a class="page-link" page-index="' + i + '" >' + i + '</a></li>\n';
                    }
                }
            }
        } else {
            for (let i = 1; i <= 5; i++) {
                if (i == current) {
                    html += '        <li class="page-item active"><a class="page-link disabled" page-index="' + i + '" >' + i + '</a></li>\n';
                } else {
                    html += '        <li class="page-item"><a class="page-link" page-index="' + i + '" >' + i + '</a></li>\n';
                }
            }
            html += '        <li class="page-item disabled"><a class="page-link disabled" page-index="-1" >...</a></li>\n';
            html += '        <li class="page-item "><a class="page-link" page-index="' + total + '" >' + total + '</a></li>\n';
        }
    }
    if (current == total) {
        html += '<li class="page-item disabled">\n' +
            '            <a class="page-link disabled"  page-index="next" aria-disabled="true">下一页</a>\n' +
            '        </li>\n';
    } else {
        html += '<li class="page-item">\n' +
            '            <a class="page-link"  page-index="next">下一页</a>\n' +
            '        </li>\n';
    }
    html += '    </ul>';
    $(selector).html(html);
    $("ul.pagination li.page-item").unbind('click');
    $("ul.pagination li.page-item:not(.disabled,.active)").click(function (event) {
        event.stopPropagation();//阻止事件冒泡
        //判断回调函数是否存在
        if (typeof callback == 'function') {
            let index = $(this).find('a.page-link').attr('page-index');//获取要到达的页数
            if (index == 'pre' || index == 'next') {
                //获取当前页面索引
                let current = $($(this).parent('ul.pagination').children("li.active")[0]).find('a.page-link').attr('page-index');
                //计算页面索引
                if (index == 'pre') {
                    index = current - 1;
                } else {
                    index = parseInt(current) + 1;
                }
            }
            //执行回调函数
            callback(index);
        }
    });
}

//收索函数
function searchArticles(key) {
    classifyMark = "kw_" + key;
    //加载文章列表模块
    $('._content-area').load('html/_article-list.html');
}

//标签按钮点击事件
function labBtnClick(selector, type) {
    let lab = $(selector).text();
    if (type && lab) {
        classifyMark = type + "_" + lab;
        $('._content-area').load('html/_article-list.html');
    }
}







