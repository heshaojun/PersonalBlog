//滚动联动
$('[scroll-linkage-target]').scroll(
    function () {
        let target = "" + $(this).attr("scroll-linkage-target");
        $(target).scrollTop($(this).scrollTop());
        $(target).scrollLeft($(this).scrollLeft());
    }
);
$(function () {
    $(".sticky-top").find("div").bind("DOMSubtreeModified", function () {
        let parent = $(this).parent(".sticky-top");
        parent.css("top", $(window).height() - parent.height());
    });
});

//加载左边面板
$('._aside-panels').load('/html/_aside-panels.html');
//加载备案信息
$('._website-record').load('/html/_website-record.html');
//加载文章详情页
$('._article-detail').load('/html/_article-detail.html');


showdown.setFlavor('github');
let converter = new showdown.Converter({noHeaderId: true});

//主页文章列表范围，全局状态变量
let home_article_scope = 'all';
//主页文章列表排列方式
let home_article_order_type = 'time';
//初始化主页文章列表
$(function () {
    get_home_article_list(10, 1);
});

//加载文章列表
function load_article_list(selector, data) {
    let html = "<div class='border-0'>";
    let list = data['list'];
    for (let i = 0; i < list.length; i++) {
        let item = list[i];
        let div = ' <a class="list-group-item list-group-item-action pt-1 pb-1  border-bottom" href="/detail?id=' + item['id'] + '">\n' +
            '        <div class="w-100"><span class="small pl-1 pr-1 bg-warning text-info">' + item['type'] + '</span><span\n' +
            '                class=" font-weight-bold ml-2">' + item['title'] + '</span></div>\n';
        if (item['summary']) {
            div += '        <div><span>' + item['summary'] + '</span></div>\n';
            div += '        <div>\n' +
                '            <span class="small">' + item['create_time'] + '</span>\n' +
                '            <span class="ml-3 small"><img class="mr-1" src="../static/icon/watch_icon.png" height="20px">' + item['visits'] + '</span>\n' +
                '            <span class="ml-3 small"><img class="mr-1" src="../static/icon/comments_icon.png" height="20px">' + item['comments'] + '</span>\n' +
                '        </div>\n'
        }
        div += '    </a>';
        html += div;
    }
    html += "</div>";
    $(selector).html(html);
}

//加分页栏
function load_pagination(selector, current, total, callback) {
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


//获取文章列表，加载文章列表和文章列表分页栏
function get_home_article_list(size, page) {
    $.get("../static/data/article_list.json?size=" + size + "&page=" + page + "&scope=" + home_article_scope + "&type=" + home_article_order_type,
        function (data, status) {
            if (status == "success") {
                load_article_list("#_home-article-list-area", data);
                load_pagination("#_home-article-list-pagination", data['current'], data['total'], function (index) {
                    get_home_article_list(size, index);
                });
            }
        })
}

//主页文章列表更改文章范围
function change_home_article_scope(selector, type) {
    if (selector && type) {
        let bros = $(selector).parent('div').children('a');
        for (let i = 0; i < bros.length; i++) {
            $(bros[i]).removeClass("text-primary disabled");
        }
        $(selector).addClass("text-primary disabled");
        home_article_scope = type;
        get_home_article_list(10, 1);
    }
}

//主页文章排列方式更改函数
function change_home_article_order(selector, type) {
    $(selector).parent("div").find("a.disabled").removeClass("text-primary disabled");
    $(selector).addClass("text-primary disabled");
    home_article_order_type = type;
}

//加载文章详细内容函数
function load_article_detail(url) {
    $.get(url, function (data, status) {
        if (status == 'success') {
            $("._article-detail-title").text(data['title']);
            load_article_detail_info(data);
            load_article_labs(data);
            load_article_content(data);
        }
    });
}

//加载文章信息
function load_article_detail_info(data) {
    let html = "";
    if (data['original']) {
        html += "<span class=\"mr-2\"><small class=\"bg-warning\">原创</small></span>";
    } else {
        html += "<span class=\"mr-2\"><small class=\"bg-warning\">非原创</small></span>";
    }
    html += "<span class=\"mr-2 \"><small>" + data['type'] + "</small></span>\n" +
        "                <span class=\"mr-2\"><small>抠脚汉</small></span>\n" +
        "                <span class=\"mr-2\"><small>" + data['create_time'] + "</small></span>\n" +
        "                <span class=\"mr-2\"><img src=\"../static/icon/watch_icon.png\" height=\"22px\"><small\n" +
        "                        class=\"pl-1\">" + data['visits'] + "</small></span>\n" +
        "                <span class=\"mr-2\"><img src=\"../static/icon/comments_icon.png\" height=\"22px\"><small\n" +
        "                        class=\"pl-1\">" + data['comments'] + "</small></span>";
    $("._article-detail-info").html(html);
}

//加载文章标签
function load_article_labs(data) {
    let html = "";
    let classify_labs = data['classify_labs'];
    let article_labs = data['article_labs'];
    if (classify_labs.length > 0) {
        html += "<div class=\"d-inline mr-2\">\n" +
            "                    <span><small>分类标签:</small></span>";
        for (let i = 0; i < classify_labs.length; i++) {
            html += "<small><a class=\"d-inline-block bg-white\" style=\"padding: 0 2px;border-radius: 4px\">" + classify_labs[i] + "</a></small>";
        }
        html += "</div>"
    }
    if (article_labs.length > 0) {
        html += "<div class=\"d-inline mr-2\">\n" +
            "                    <span><small>文章标签:</small></span>";
        for (let i = 0; i < article_labs.length; i++) {
            html += "<small><a class=\"d-inline-block bg-white\" style=\"padding: 0 2px;border-radius: 4px\">" + article_labs[i] + "</a></small>";
        }
        html += "</div>"
    }
    $('._article-detail-labs').html(html);
}

// 加载正文
function load_article_content(data) {
    let html = converter.makeHtml(data['content']);
    $("._article-detail-content").html(html);
}
