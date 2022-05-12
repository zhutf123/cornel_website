import { defineConfig } from 'vite'
import {createVuePlugin} from 'vite-plugin-vue2'

// https://vitejs.dev/config/
export default defineConfig({
  base: '',
  runtimeCompiler: false,
  build: {
    outDir: 'admin_web'
  },
  rollupInputOptions: {
    external: ['vue']
  },
  plugins: [
    createVuePlugin()
  ]
})
