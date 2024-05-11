function success(main) {
    const child = document.getElementById("main-body");
    const parent = child.parentElement;
    parent.removeChild(child);
    parent.insertAdjacentHTML('beforeend', main);
}

function getLink() {
    var link = window.location.href.split('/').pop();
    return link.split('?')[0];
}

function updateQueryStringParameter(uri, key, value) {
    var re = new RegExp("([?&])" + key + "=.*?(&|$)", "i");
    var separator = uri.indexOf('?') !== -1 ? "&" : "?";
    if (uri.match(re)) {
        return uri.replace(re, '$1' + key + "=" + value + '$2');
    }
    else {
        return uri + separator + key + "=" + value;
    }
}

$(document).ready(function() {
    $("#chapters-button").click(function(){
        window.location.href = updateQueryStringParameter(window.location.href, "part", "chapters")
    });
});

$(document).ready(function() {
    $("#settings-button").click(function(){
        window.location.href = updateQueryStringParameter(window.location.href, "part", "settings")
    });
});