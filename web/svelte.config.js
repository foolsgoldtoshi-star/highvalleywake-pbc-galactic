import adapter from '@sveltejs/adapter-static';

/** @type {import('@sveltejs/kit').Config} */
const config = {
	kit: {
		adapter: adapter({
			pages: 'dist',
			assets: 'dist',
			fallback: 'index.html',
			precompress: false,
			strict: true
		}),
		prerender: {
			handleHttpError: 'warn',
			handleMissingId: 'warn',
			entries: [
				'/',
				'/en',
				'/en/wiki/roadmap',
				'/en/wiki/integration',
				'/en/wiki/mmt',
				'/en/wiki/blockchain'
			]
		},
		paths: {
			base: ''  // No base path for localhost development
		},
		alias: {
			$content: 'static/content',
			$docs: 'src/lib/docs'
		}
	}
};

export default config;

// 🌱 Localhost development optimized configuration
// 🔧 Relative paths for seamless development
// 🎊 Part of the galactic dual build system
