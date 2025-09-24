/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: false,
  output: 'standalone',
  async rewrites() {
    return [
       {
        source: '/booking/:path*',
        destination: `http://rbp-booking:3000/booking/:path*`,
      },
      {
        source: '/room/:path*',
        destination: `http://rbp-room:3001/room/:path*`,
      },
      {
        source: '/brand/:path*',
        destination: `http://rbp-branding:3002/brand/:path*`,
      },
      {
        source: '/auth/:path*',
        destination: `http://rbp-auth:3004/auth/:path*`,
      },
      {
        source: '/report/:path*',
        destination: `http://rbp-report:3005/report/:path*`,
      },
      {
        source: '/message/:path*',
        destination: `http://rbp-message:3006/message/:path*`,
      },
      {
        source: '/booking/swagger-ui/:path*',
        destination: `http://rbp-booking:3000/booking/swagger-ui/:path*`,
      },
      {
        source: '/booking/v3/:path*',
        destination: `http://rbp-booking:3000/booking/v3/:path*`,
      },
      {
        source: '/room/swagger-ui/:path*',
        destination: `http://rbp-room:3001/room/swagger-ui/:path*`,
      },
      {
        source: '/room/v3/:path*',
        destination: `http://rbp-room:3001/room/v3/:path*`,
      },
      {
        source: '/brand/swagger-ui/:path*',
        destination: `http://rbp-branding:3002/brand/swagger-ui/:path*`,
      },
      {
        source: '/brand/v3/:path*',
        destination: `http://rbp-brand:3002/brand/v3/:path*`,
      },
      {
        source: '/auth/swagger-ui/:path*',
        destination: `http://rbp-auth:3004/auth/swagger-ui/:path*`,
      },
      {
        source: '/auth/v3/:path*',
        destination: `http://rbp-auth:3004/auth/v3/:path*`,
      },
      {
        source: '/report/swagger-ui/:path*',
        destination: `http://rbp-report:3005/report/swagger-ui/:path*`,
      },
      {
        source: '/report/v3/:path*',
        destination: `http://rbp-report:3005/report/v3/:path*`,
      },
      {
        source: '/message/swagger-ui/:path*',
        destination: `http://rbp-message:3006/message/swagger-ui/:path*`,
      },
      {
        source: '/message/v3/:path*',
        destination: `http://rbp-message:3006/message/v3/:path*`,
      }
    ];
  }
};

module.exports = nextConfig;
