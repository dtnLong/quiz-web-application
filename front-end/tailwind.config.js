module.exports = {
  mode: 'jit',
  purge: ['./index.html', './src/**/*.{vue,js,ts,jsx,tsx}'],
  darkMode: false, // or 'media' or 'class'
  theme: {
    fontFamily: {
      'sans': ['Inter', 'sans-serif', 'system-ui']
    },
    extend: {
      colors: {
        'form-bg': '#FFF6F3',
        'error-500': '#E0483B',
        'error-800': '#4C0900',
        'error-900': '#160207',
        'pink-custom': '#D90971',
        'input-bg': '#FFECE5',

      }
    },
  },
  variants: {
    extend: {},
  },
  plugins: [],
}
