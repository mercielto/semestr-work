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

$(document).ready(function() {
    $("#chapters-button").click(function(){
        window.location.href = "/post/create/chapters/" + getLink();
    });
});

$(document).ready(function() {
    $("#settings-button").click(function(){
        window.location.href = "/post/create/settings/" + getLink();
    });
});