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


$(document).ready(function() {
    $(".chapter-add").click(function(){
        const linkElement = document.getElementById('branchName');
        const hrefValue = linkElement.getAttribute('href');
        const branchLink = hrefValue.split('/').pop();
        window.location.href = "/post/create/chapter-add/" + branchLink;
    });
});

const chapterDividers = document.querySelectorAll('.chapter-divider');

chapterDividers.forEach(divider => {
    const addButton = divider.querySelector('.chapter-add');

    divider.addEventListener('mouseenter', function() {
        addButton.style.display = 'inline-block';
    });

    divider.addEventListener('mouseleave', function() {
        addButton.style.display = 'none';
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