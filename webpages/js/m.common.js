//滚动联动
$('[scroll-linkage-target]').scroll(
    function () {
        let target = "#" + $(this).attr("scroll-linkage-target");
        $(target).scrollTop($(this).scrollTop());
        $(target).scrollLeft($(this).scrollLeft());
    }
);
//加载导航栏
$('._home-navigator').load('_home-navigator.html');
//加载文章列表模块
$('._article-list').load('_article-list.html');
//加载左边面板
$('._aside-panels').load('_aside-panels.html');
//加载备案信息
$('._website-record').load('_website-record.html');

//加载文章列表
function load_article_list(selector, data) {
    let html = "<div class='list-group border-0'>";
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
            '            <a class="page-link" href="#" data-mark="pre" aria-disabled="true">上一页</a>\n' +
            '        </li>\n';
    } else {
        html += '<li class="page-item">\n' +
            '            <a class="page-link" href="#" data-mark="pre">上一页</a>\n' +
            '        </li>\n';
    }
    if (total <= 7) {
        for (let i = 1; i <= total; i++) {
            if (i == current) {
                html += '        <li class="page-item active"><a class="page-link" page-index="' + i + '" href="#">' + i + '</a></li>\n';
                continue;
            }
            html += '        <li class="page-item"><a class="page-link" page-index="' + i + '" href="#">' + i + '</a></li>\n';
        }
    } else {
        if (current - 1 > 3) {
            html += '        <li class="page-item "><a class="page-link" page-index="1" href="#">1</a></li>\n';
            html += '        <li class="page-item disabled"><a class="page-link" page-index="-1" href="#">...</a></li>\n';
            if (total - current > 3) {
                html += '        <li class="page-item "><a class="page-link" page-index="' + (current - 1) + '" href="#">' + (current - 1) + '</a></li>\n';
                html += '        <li class="page-item active"><a class="page-link" page-index="' + current + '" href="#">' + current + '</a></li>\n';
                html += '        <li class="page-item "><a class="page-link" page-index="' + (current - 1) + '" href="#">' + (current - 1) + '</a></li>\n';
                html += '        <li class="page-item disabled"><a class="page-link" page-index="-1" href="#">...</a></li>\n';
                html += '        <li class="page-item active"><a class="page-link" page-index="' + total + '" href="#">' + total + '</a></li>\n';
            } else {
                for (let i = total - 4; i <= total; i++) {
                    if (i == current) {
                        html += '        <li class="page-item active"><a class="page-link" page-index="' + i + '" href="#">' + i + '</a></li>\n';
                    } else {
                        html += '        <li class="page-item"><a class="page-link" page-index="' + i + '" href="#">' + i + '</a></li>\n';
                    }
                }
            }
        } else {
            for (let i = 1; i <= 5; i++) {
                if (i == current) {
                    html += '        <li class="page-item active"><a class="page-link" page-index="' + i + '" href="#">' + i + '</a></li>\n';
                } else {
                    html += '        <li class="page-item"><a class="page-link" page-index="' + i + '" href="#">' + i + '</a></li>\n';
                }
            }
            html += '        <li class="page-item disabled"><a class="page-link" page-index="-1" href="#">...</a></li>\n';
            html += '        <li class="page-item "><a class="page-link" page-index="' + total + '" href="#">' + total + '</a></li>\n';
        }
    }
    if (current == total) {
        html += '<li class="page-item disabled">\n' +
            '            <a class="page-link" href="#" data-mark="next" aria-disabled="true">下一页</a>\n' +
            '        </li>\n';
    } else {
        html += '<li class="page-item">\n' +
            '            <a class="page-link" href="#" data-mark="next">下一页</a>\n' +
            '        </li>\n';
    }
    html += '    </ul>';
    $(selector).html(html);
    $("ul.pagination li.page-item").unbind('click');
    $("ul.pagination li.page-item").click(function (event) {
        event.stopPropagation();//阻止事件冒泡
        console.log($(event.currentTarget).parent());
    });
}