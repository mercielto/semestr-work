const pageSize = 5;

document.addEventListener("DOMContentLoaded", function() {
    const stars = document.querySelectorAll('.rating input[type="radio"]');

    stars.forEach(function(star) {
        star.addEventListener("click", function(event) {
            const clickedStarValue = parseInt(this.value);

            var excerpt = event.target.closest('.excerpt');
            var linkElement = excerpt.querySelector('h2 a');
            var href = linkElement.getAttribute('href');
            var link = href.substring(href.lastIndexOf('/') + 1);

            var data = {
                "rate": clickedStarValue
            }

            $.ajax({
                url: "/branch/ajax/rate/" + link,
                type: "PUT",
                data: JSON.stringify(data),
                contentType: "application/json",
                success: function(response) {
                    console.log("User rated:", clickedStarValue);
                },
                error: function(xhr, status, error) {
                    console.log("Error: " + status)
                }
            });
        });
    });
});

function createBranches(branches) {
    const container = document.getElementById('container');
    branches.forEach(branch => {
        container.innerHTML += createBranch(branch);
    });
}

function clearAndCreateBranches(branches) {
    const container = document.getElementById('container');
    const children = container.children;
    for (let i = children.length - 1; i >= 0; i--) {
        const child = children[i];
        if (child.classList.contains('excerpt')) {
            container.removeChild(child);
        }
    }
    branches.forEach(branch => {
        container.innerHTML += createBranch(branch);
    });
}


function createBranch(obj) {
    const branch = obj['response'];
    const numbers = Array.from({ length: 10 }, (_, i) => i + 1).reverse();
    const ratingValue = obj['rating'];

    return `
                <div class="excerpt" data-rating="0" data-votes="0">
                    <h2><a href="/branch/profile/${branch['link']}">${branch['name']}</a></h2>
                    <div class="rating">
                        ${numbers.map(number => {
        const r = number;
        return `
                                <input type="radio" id="star${number}-${branch['link']}" name="rating-${branch['link']}" value="${r}"
                                       ${ratingValue > r ? 'checked' : ''}/>
                                <label for="star${number}-${branch['link']}">☆</label>
                            `;
    }).join('')}
                    </div>
                    <p class="rating-info">Average Rating: ${branch['rating']} (${branch['votersCount']} votes)</p>
                    <div class="excerpt-content">
                        <p>${branch['description'] ? branch['description'] : ''}</p>
                    </div>
                </div>
            `;
}

document.addEventListener("DOMContentLoaded", function() {
    var container = document.querySelector('.container');
    const loadMoreBtn = document.getElementById('load-more-btn');

    if (loadMoreBtn === null) {
        return;
    }

    loadMoreBtn.addEventListener('click', function() {
        var container = document.querySelector('.container');
        var excerpts = container.querySelectorAll('.excerpt');
        var count = excerpts.length;
        var link = window.location.href.split("/").pop().split("?")[0];
        var sort = document.getElementById("sort");


        $.ajax({
            url: '/branch/ajax/' + link + '?from=' + ~~(count / pageSize) + "&count="
                + pageSize + "&sort=" + sort.value,
            success: function(response) {
                if (response.length !== pageSize) {
                    loadMoreBtn.parentElement.removeChild(loadMoreBtn)
                }
                createBranches(response);
            },
            error: function(xhr, status, error) {
                console.log("Error: " + status)
            }
        });

    });
});

document.addEventListener("DOMContentLoaded", function() {
    const sortDropdown = document.querySelector('.sort-dropdown');
    const sortSelect = sortDropdown.querySelector('#sort');

    sortSelect.addEventListener('change', function() {
        const selectedValue = this.value;

        $.ajax({
            url: '/branch/ajax/' + link + '?from=' + 0 + "&count="
                + pageSize + "&sort=" + selectedValue,
            success: function(response) {
                console.log(response);
                clearAndCreateBranches(response);
            },
            error: function(xhr, status, error) {
                console.log("Error: " + status)
            }
        });


    });
});
