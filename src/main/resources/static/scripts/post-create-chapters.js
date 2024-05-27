function updateChapterNumbers() {
    const chapters = document.querySelectorAll('.chapter');
    chapters.forEach((chapter, index) => {
        const chapterName = chapter.querySelector('.chapter-name p');
        const chapterTitle = chapterName.innerText.split('. ')[1];
        chapterName.innerText = `${index + 1}. ${chapterTitle}`;
    });
}

function replaceBody(button) {
    const chapterElement = button.closest('.chapter');
    if (chapterElement) {
        chapterElement.remove();

        updateChapterNumbers();
    }
}

function changeUrlParameterWithPushState(param, value) {
    var currentUrl = new URL(window.location);
    currentUrl.searchParams.set(param, value);
    var stateObj = { foo: "bar" };
    history.pushState(stateObj, "", currentUrl);
}


$(document).ready(function() {
    $(".chapter-add").click(function(){
        const linkElement = document.getElementById('branchName');
        const hrefValue = linkElement.getAttribute('href');
        const branchLink = hrefValue.split('/').pop();
        const number = document.getElementById('branchNumber')
        window.location.href = "/branch/create/profile?branchLink=" + branchLink + "&number=" + number.value;
    });
});

const deleteButtons = document.querySelectorAll('.delete-chapter-btn');

deleteButtons.forEach(button => {
    button.addEventListener('click', function() {
        const chapter = button.closest('.chapter');
        const confirmDelete = window.confirm('Вы уверены, что хотите удалить эту главу?');

        if (confirmDelete) {
            const chapterLink = chapter.querySelector('.chapter-name').href.split("/").pop();

            $.ajax({
                url: "/chapter/ajax/" + chapterLink,
                type: "delete",
                contentType: "application/json",
                success: function(data) {
                    console.log("Success");
                    replaceBody(button);
                },
                error: function(xhr, status, error) {
                    console.error("Error:", error);
                }
            });


        }
    });
});

function changeBranchName(branch) {
    const a = document.getElementById("branchName");
    a.href = "/branch/create/profile/" + branch["link"];
    a.innerText = branch["name"];
}

document.addEventListener("DOMContentLoaded", function() {

    const chaptersContainer = document.getElementById('contents');

    function removeChaptersAfter(selected) {
        let nextSibling = selected.nextElementSibling;
        while (nextSibling) {
            let toRemove = nextSibling;
            nextSibling = nextSibling.nextElementSibling;
            chaptersContainer.removeChild(toRemove);
        }
    }

    function addChangeListenerToSelect(chapterSelect) {
        chapterSelect.addEventListener("change", function() {
            const selectedOption = chapterSelect.options[chapterSelect.selectedIndex];
            const branchLink = selectedOption.dataset.link;
            const chapterLink = selectedOption.dataset.chapterlink;
            console.log("Selected value:", chapterSelect.value, "link:", branchLink);

            const a = chapterSelect.closest("div").querySelector("a.chapter-name");
            a.href = '/post/create/chapter/' + chapterLink;
            const number = a.dataset.number;

            changeUrlParameterWithPushState("branch", branchLink);

            function addChapter(chapterMap) {
                for (const [key, chaptersList] of Object.entries(chapterMap)) {

                    if (key <= number) {
                        continue;
                    }

                    const selected = chaptersList[0];
                    const divElement = document.createElement('div');
                    divElement.className = "chapter";

                    const a = document.createElement('a');
                    a.className = 'chapter-name';
                    a.href = `/post/create/chapter/${selected.link}`;
                    a.textContent = `Глава ${key}:`;
                    a.setAttribute("data-number", key);

                    const btn = document.createElement("button");
                    btn.className = 'delete-chapter-btn';
                    btn.innerText = "Delete";

                    if (chaptersList.size > 1) {
                        btn.style = "display: none";
                    }

                    btn.addEventListener('click', function() {
                        const chapter = btn.closest('.chapter');
                        const confirmDelete = window.confirm('Вы уверены, что хотите удалить эту главу?');

                        if (confirmDelete) {
                            const chapterLink = chapter.querySelector('.chapter-name').href.split("/").pop();

                            $.ajax({
                                url: "/chapter/ajax/" + chapterLink,
                                type: "delete",
                                contentType: "application/json",
                                success: function(data) {
                                    console.log("Success");
                                    replaceBody(btn);
                                },
                                error: function(xhr, status, error) {
                                    console.error("Error:", error);
                                }
                            });
                        }
                    });

                    divElement.appendChild(a);

                    if (chaptersList.length > 1) {
                        const select = document.createElement('select');
                        select.className = 'chapter-select';

                        chaptersList.forEach(chapter => {
                            const option = document.createElement('option');
                            option.setAttribute('data-link', chapter.branchLink);
                            option.setAttribute('data-chapterlink', chapter.link);
                            option.textContent = chapter.title;
                            select.appendChild(option);
                        });

                        divElement.appendChild(select);
                        addChangeListenerToSelect(select);
                    } else {
                        const span = document.createElement('span');
                        span.textContent = " " + selected.title;
                        divElement.appendChild(span);
                    }
                    divElement.appendChild(btn);

                    chaptersContainer.appendChild(divElement);
                }
            }

            $.ajax({
                url: "/branch/ajax/content/" + branchLink,
                type: "get",
                contentType: "application/json",
                success: function(response) {
                    console.log(response);
                    const parentLi = chapterSelect.closest("div");
                    removeChaptersAfter(parentLi);
                    addChapter(response["chapters"]);
                    changeBranchName(response["branch"]);
                },
                error: function(xhr, status, error) {
                    console.error('There was a problem:', error);
                }
            });
        });
    }

    const chapterSelects = document.querySelectorAll(".chapter-select");
    chapterSelects.forEach(addChangeListenerToSelect);
});
