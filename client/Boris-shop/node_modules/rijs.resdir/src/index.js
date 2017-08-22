// -------------------------------------------
// Loads resources from the /resources folder
// -------------------------------------------
export default function resdir(ripple, { dir = '.', watch = isNonProd(), glob = '/resources/**/!(*test).{js,css}' } = {}){
  log('creating', { watch })
  const argv = require('minimist')(process.argv.slice(2))
      , folders = (argv.r || argv.resdirs || '')
          .split(',')
          .concat(dir)
          .filter(Boolean)
          .map(d => resolve(d))
          .map(append(glob))
      , watcher = chokidar.watch(folders, { ignored: /\b_/ })
          .on('error', err)
          .on('add', register(ripple))
          .on('change', register(ripple))
          .on('ready', async () => {
            if (!watch) watcher.close()
            await Promise.all(values(ripple.resources)
              .map(loaded(ripple)))

            def(ripple, 'ready', true)
            ripple.emit('ready')
          })

  return ripple
}

const register = ripple => path => {
  var last = basename(path)
    , isJS = extname(path) == '.js'
    , name = isJS ? last.replace('.js', '') : last
    , cach = delete require.cache[path]
    , body = (isJS ? require : file)(path)
    , css  = isJS && exists(path.replace('.js', '.css'))
    , res  = is.obj(body = body.default || body) ? body 
           : css ? { name, body, headers: { needs: '[css]' } } 
                 : { name, body } 

  ripple(res)

  if (ripple.ready) 
    loaded(ripple)(ripple.resources[res.name])
}

function isNonProd(){
  return lo(process.env.NODE_ENV) != 'prod' && lo(process.env.NODE_ENV) != 'production'
}

import { resolve, basename, extname } from 'path'
import { existsSync as exists } from 'fs'
import chokidar from 'chokidar'
import append from 'utilise/append'
import values from 'utilise/values'
import file from 'utilise/file'
import def from 'utilise/def'
import is from 'utilise/is'
import lo from 'utilise/lo'
const log = require('utilise/log')('[ri/resdir]')
    , err = require('utilise/err')('[ri/resdir]')
    , loaded = ripple => res => is.fn(res.headers.loaded) && res.headers.loaded(ripple, res)