// sideNav.js

document.addEventListener('DOMContentLoaded', () => {
    const navLinks = document.querySelectorAll('.side-nav a');
    
    navLinks.forEach(link => {
        link.addEventListener('click', (event) => {
            event.preventDefault();
            const pageUrl = link.getAttribute('href');
            fetchPageContent(pageUrl);
        });
    });
});

function fetchPageContent(url) {
    fetch(url)
        .then(response => response.text())
        .then(html => {
            document.querySelector('.content').innerHTML = html;
        })
        .catch(error => console.error('Error fetching page content:', error));
}
