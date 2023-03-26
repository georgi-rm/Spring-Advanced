const host = 'http://localhost:8080';

async function request(url, method, data) {
    const options = {
        method,
        headers: {}
    }

    if (data !== undefined) {
        options.headers['Content-Type'] = 'application/json';
        options.body = JSON.stringify(data);
    }

    try {
        const response = await fetch(host + url, options);

        if (!response.ok) {
            const error = await response.json();
            throw new Error(`${error.status}: ${error.error}\n${error.message}`);
        }

        if (response.status === 204) {
            return response;
        } else {
            return await response.json();
        }
    } catch (e) {
        alert(e.message);
        throw e;
    }
}

export async function get(url) {
    return request(url, 'get');
}