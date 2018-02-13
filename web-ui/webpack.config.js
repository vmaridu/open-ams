const webpack = require('webpack');
const CleanWebpackPlugin = require('clean-webpack-plugin');

const config = {
  entry: './src/scripts/app.js',
  output: {
    path: __dirname + '/dist',
    filename: 'bundle.js'
  },
  module: {
    rules: [
      {
        test: /\.js$/,
        exclude: /node_modules/,
        use: [
          {
            loader: 'babel-loader',
            query: {
              presets: ['es2015']
            }
          }
        ]
      },

      {
        test: /\.css$/,
        use: ['style-loader', 'css-loader']
      },

      {
        test: /\.hbs$/,
        use: ['handlebars-loader' ]
      },

      {
        test: /\.js$/,
        use: ["source-map-loader"],
        enforce: "pre"
      }

    ]
  },
  plugins: [
    new webpack.ProvidePlugin({
      $: 'jquery',
      jQuery: 'jquery'
    }),
    new webpack.ProvidePlugin({
      _: 'lodash',
      lodash: 'lodash'
    }),
    new CleanWebpackPlugin(['dist'])
  ]

}

module.exports = config;