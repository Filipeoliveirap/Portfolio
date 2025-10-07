

/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./app/**/*.{js,jsx,ts,tsx}",
    "./components/**/*.{js,jsx,ts,tsx}",
    "./screens/**/*.{js,jsx,ts,tsx}", // caso queira incluir as telas
  ],
  presets: [require("nativewind/preset")],
  theme: {
    extend: {
      colors: {
        primary: "#4F46E5", // roxo
        "primary-foreground": "#FFFFFF",
        secondary: "#F43F5E", // rosa/vermelho
        "secondary-foreground": "#FFFFFF",
        destructive: "#DC2626",
        "destructive-foreground": "#FFFFFF",
        muted: "#6B7280", // cinza
        "muted-foreground": "#FFFFFF",
        accent: "#F59E0B", // amarelo
        "accent-foreground": "#000000",
        border: "#E5E7EB",
        input: "#F3F4F6",
        ring: "#6366F1",
        background: "#FFFFFF",
        foreground: "#111827",
        popover: "#FFFFFF",
        "popover-foreground": "#111827",
        card: "#FFFFFF",
        "card-foreground": "#111827",
      },
      borderWidth: {
        hairline: "1px",
      },
      fontFamily: {
        sans: ["Inter", "sans-serif"],
      },
    },
  },
  plugins: [],
};

