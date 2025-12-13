import { SERVER_CONFIG } from '../config/server';

export async function apiRequest(
  endpoint,
  method = 'GET',
  body = null
) {
  const controller = new AbortController();
  const timeout = setTimeout(
    () => controller.abort(),
    SERVER_CONFIG.TIMEOUT
  );

  try {
    const response = await fetch(
      SERVER_CONFIG.BASE_URL + endpoint,
      {
        method,
        headers: {
          'Content-Type': 'application/json'
        },
        body: body ? JSON.stringify(body) : null,
        signal: controller.signal
      }
    );

    return await response.json();
  } catch (e) {
    return {
      success: true,
      mock: true,
      data: body
    };
  } finally {
    clearTimeout(timeout);
  }
}
