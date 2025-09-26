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
			base: '/highvalleywake-pbc-galactic'  // GitHub Pages path
		},
		alias: {
			$content: 'static/content',
			$docs: 'src/lib/docs'
		}
	}
};

export default config;

// 🌍 GitHub Pages optimized configuration
// 🚀 Production deployment with proper base paths
// 🎊 Part of the galactic dual build system
