import { defineConfig } from 'vite'
import {createVuePlugin} from 'vite-plugin-vue2'

// https://vitejs.dev/config/
export default defineConfig({
  server: {
    proxy: {
      '^/admin.*': {
        target: 'http://wx.ydwlys.com',
        secure: false,
        changeOrigin: true
      }
    }
  },
  runtimeCompiler: false,
  rollupInputOptions: {
    external: ['vue']
  },
  plugins: [
    createVuePlugin()
  ]
})
