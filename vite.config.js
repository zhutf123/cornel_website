import { defineConfig } from 'vite'
import {createVuePlugin} from 'vite-plugin-vue2'

// https://vitejs.dev/config/
export default defineConfig({
  runtimeCompiler: false,
  rollupInputOptions: {
    external: ['vue']
  },
  plugins: [
    createVuePlugin()
  ]
})
