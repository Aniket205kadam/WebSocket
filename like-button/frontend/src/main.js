// connect to the web-socket
const socket = new SockJS('http://localhost:8080/ws');
const stompClient = Stomp.over(socket);

// like the post
function sendLike(postId) {
    if (stompClient.connected) {
        stompClient.send(`/app/${postId}/like`, {}, JSON.stringify({}));
    } else {
        console.error("WebSocket is not connected. Unable to send like.");
    }
}

const postContainer = document.querySelector('#postContainer');
const apiEndpoint = "http://localhost:8080/api/v1/posts";

// fetch the post form the data-base
async function fetchPosts() {
    try {
        const response = await fetch(apiEndpoint, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        const posts = await response.json();

        // Render the post
        posts.forEach(post => {
            const postCard = document.createElement('div');
            postCard.className = "post-card";
            postCard.id = `${post.id}`;

            const profileUrl = document.createElement('img');
            profileUrl.src = post.userProfileUrl;
            profileUrl.alt = `${post.username} profile picture`;
            profileUrl.className = "profile-img";

            const username = document.createElement('h3');
            username.textContent = post.username;

            const caption = document.createElement('p');
            caption.textContent = post.caption;

            const postImg = document.createElement('img');
            postImg.src = post.imageUrl;
            postImg.alt = "post-img";
            postImg.className = "post-img";

            const likes = document.createElement('p');
            likes.className = "like-count";
            likes.textContent = `${post.likes === null ? 0 : post.likes} Likes`;

            const likeButton = document.createElement('button');
            likeButton.textContent = 'Like';
            likeButton.className = "like-button";

            likeButton.addEventListener('click', () => {
                likeButton.classList.add('after-btn-click');
                sendLike(post.id);
            });

            postCard.appendChild(profileUrl);
            postCard.appendChild(username);
            postCard.appendChild(caption);
            postCard.appendChild(postImg);
            postCard.appendChild(likes);
            postCard.appendChild(likeButton);

            postContainer.appendChild(postCard);
        });

        // subsribe to specific topic
        stompClient.connect({}, function (frame) {
            posts.forEach(post => {
                stompClient.subscribe(`/users/${post.id}/likes`, (message) => {
                    const data = JSON.parse(message.body);
                    const postId = data.postId;
                    const updatedLikes = data.likes;
                    const postCard = document.querySelector(`#${CSS.escape(postId)}`);
                    const likesElement = postCard.querySelector('.like-count');
                    likesElement.textContent = `${updatedLikes} Likes`;
                });
            });
        });

    } catch (error) {
        console.error("Error fetching posts:", error);
    }
}

fetchPosts();
