/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: false,
  output: 'standalone',
  async rewrites() {
    return [
      {
        source: '/api/booking/actuator/:path*',
        destination: `http://rbp-booking:3000/booking/actuator/:path*`,
      },
      {
        source: '/api/room/actuator/:path*',
        destination: `http://rbp-room:3001/room/actuator/:path*`,
      },
      {
        source: '/api/branding/actuator/:path*',
        destination: `http://rbp-branding:3002/branding/actuator/:path*`,
      },
      {
        source: '/api/auth/actuator/:path*',
        destination: `http://rbp-auth:3004/auth/actuator/:path*`,
      },
      {
        source: '/api/report/actuator/:path*',
        destination: `http://rbp-report:3005/report/actuator/:path*`,
      },
      {
        source: '/api/message/actuator/:path*',
        destination: `http://rbp-message:3006/message/actuator/:path*`,
      },
      {
        source: '/api/booking/swagger-ui/:path*',
        destination: `http://rbp-booking:3000/booking/swagger-ui/:path*`,
      },
      {
        source: '/api/booking/v3/:path*',
        destination: `http://rbp-booking:3000/booking/v3/:path*`,
      },
      {
        source: '/api/room/swagger-ui/:path*',
        destination: `http://rbp-room:3001/room/swagger-ui/:path*`,
      },
      {
        source: '/api/room/v3/:path*',
        destination: `http://rbp-room:3001/room/v3/:path*`,
      },
      {
        source: '/api/branding/swagger-ui/:path*',
        destination: `http://rbp-branding:3002/branding/swagger-ui/:path*`,
      },
      {
        source: '/api/branding/v3/:path*',
        destination: `http://rbp-branding:3002/branding/v3/:path*`,
      },
      {
        source: '/api/auth/swagger-ui/:path*',
        destination: `http://rbp-auth:3004/auth/swagger-ui/:path*`,
      },
      {
        source: '/api/auth/v3/:path*',
        destination: `http://rbp-auth:3004/auth/v3/:path*`,
      },
      {
        source: '/api/report/swagger-ui/:path*',
        destination: `http://rbp-report:3005/report/swagger-ui/:path*`,
      },
      {
        source: '/api/report/v3/:path*',
        destination: `http://rbp-report:3005/report/v3/:path*`,
      },
      {
        source: '/api/message/swagger-ui/:path*',
        destination: `http://rbp-message:3006/message/swagger-ui/:path*`,
      },
      {
        source: '/api/message/v3/:path*',
        destination: `http://rbp-message:3006/message/v3/:path*`,
      }
    ];
  }
};

module.exports = nextConfig;
