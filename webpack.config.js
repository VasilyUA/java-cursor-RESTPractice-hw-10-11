const path = require('path');

module.exports = {
    entry: path.resolve(__dirname, 'client', 'index.js'),
    output: {
        path: path.resolve(__dirname, 'src', 'main', 'resources', 'static'),
        filename: 'bundle.js',
        publicPath: '/',
    },
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env', '@babel/preset-react'],
                    },
                },
            },
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader'],
            },
        ],
    },
    resolve: {
        extensions: ['.js', '.jsx'],
    },
    devServer: {
        static: {
            directory: path.join(__dirname, 'src', 'main', 'resources', 'templates'),
        },
        compress: true,
        port: 3000,
        watchFiles: [
            path.join(__dirname, 'src', 'main', 'resources', 'templates', 'index.html'),
            path.join(__dirname, 'src', 'main', 'resources', 'static', 'bundle.js'),
        ],
        historyApiFallback: {
            rewrites: [
                {
                    from: /^\/$/,
                    to: '/src/main/resources/templates/index.html',
                },
            ],
        },
    },

};