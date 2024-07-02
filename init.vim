set nocompatible " 关闭 vi 兼容模式
set list
set listchars=tab:>-,trail:-
set foldmethod=manual
set fencs=utf-8,ucs-bom,shift-jis,gb18030,gbk,gb2312,cp936
set termencoding=utf-8
set encoding=utf-8
set fileencoding=utf-8
set noeb
set background=light
" 自动缩进
"set autoindent
"set cindent
" Tab键的宽度
set expandtab
set tabstop=4
" 统一缩进为4
set softtabstop=4
set shiftwidth=4

syntax on " 自动语法高亮
"colorscheme autumn "设定配色方案
" colorscheme gotham256
"colorscheme molokai
set number " 显示行号
set cursorline " 突出显示当前行
set ruler " 打开状态栏标尺
set nobackup " 覆盖文件时不备份
"let g:molokai_original = 1
set mouse=v
set backspace=indent,eol,start
" call plug#begin()
"
" " List your plugins here
" Plug 'tpope/vim-sensible'
"
" " vim-plug
" Plug 'whatyouhide/vim-gotham'
"
" call plug#end()
set termguicolors

call plug#begin()
" The default plugin directory will be as follows:
"   - Vim (Linux/macOS): '~/.vim/plugged'
"   - Vim (Windows): '~/vimfiles/plugged'
"   - Neovim (Linux/macOS/Windows): stdpath('data') . '/plugged'
" You can specify a custom plugin directory by passing it as the argument
"   - e.g. `call plug#begin('~/.vim/plugged')`
"   - Avoid using standard Vim directory names like 'plugin'

" Make sure you use single quotes

" Shorthand notation for GitHub; translates to https://github.com/junegunn/seoul256.vim.git
Plug 'Exafunction/codeium.vim', { 'branch': 'main' }

Plug 'itchyny/lightline.vim'
Plug 'preservim/nerdtree'
" Any valid git URL is allowed
Plug 'https://github.com/junegunn/vim-easy-align.git'

" Using a tagged release; wildcard allowed (requires git 1.9.2 or above)
Plug 'fatih/vim-go', { 'tag': '*' }

Plug 'whatyouhide/vim-gotham'


" Using a non-default branch
Plug 'neoclide/coc.nvim', { 'branch': 'release' }


" On-demand loading: loaded when a file with a specific file type is opened
Plug 'tpope/vim-fireplace', { 'for': 'clojure' }


" Call plug#end to update &runtimepath and initialize the plugin system.
" - It automatically executes `filetype plugin indent on` and `syntax enable`
call plug#end()
" You can revert the settings after the call like so:
"   filetype indent off   " Disable file-type-specific indentation
"   syntax off            " Disable syntax highlighting

" Color schemes should be loaded after plug#end().
" We prepend it with 'silent!' to ignore errors when it's not yet installed.
silent! colorscheme gotham256
set laststatus=2