webpackJsonp([1,3],{

/***/ 1265:
/***/ (function(module, exports, __webpack_require__) {

__webpack_require__(566);
module.exports = __webpack_require__(565);


/***/ }),

/***/ 281:
/***/ (function(module, exports, __webpack_require__) {

"use strict";


exports.byteLength = byteLength
exports.toByteArray = toByteArray
exports.fromByteArray = fromByteArray

var lookup = []
var revLookup = []
var Arr = typeof Uint8Array !== 'undefined' ? Uint8Array : Array

var code = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'
for (var i = 0, len = code.length; i < len; ++i) {
  lookup[i] = code[i]
  revLookup[code.charCodeAt(i)] = i
}

revLookup['-'.charCodeAt(0)] = 62
revLookup['_'.charCodeAt(0)] = 63

function placeHoldersCount (b64) {
  var len = b64.length
  if (len % 4 > 0) {
    throw new Error('Invalid string. Length must be a multiple of 4')
  }

  // the number of equal signs (place holders)
  // if there are two placeholders, than the two characters before it
  // represent one byte
  // if there is only one, then the three characters before it represent 2 bytes
  // this is just a cheap hack to not do indexOf twice
  return b64[len - 2] === '=' ? 2 : b64[len - 1] === '=' ? 1 : 0
}

function byteLength (b64) {
  // base64 is 4/3 + up to two characters of the original data
  return b64.length * 3 / 4 - placeHoldersCount(b64)
}

function toByteArray (b64) {
  var i, j, l, tmp, placeHolders, arr
  var len = b64.length
  placeHolders = placeHoldersCount(b64)

  arr = new Arr(len * 3 / 4 - placeHolders)

  // if there are placeholders, only get up to the last complete 4 chars
  l = placeHolders > 0 ? len - 4 : len

  var L = 0

  for (i = 0, j = 0; i < l; i += 4, j += 3) {
    tmp = (revLookup[b64.charCodeAt(i)] << 18) | (revLookup[b64.charCodeAt(i + 1)] << 12) | (revLookup[b64.charCodeAt(i + 2)] << 6) | revLookup[b64.charCodeAt(i + 3)]
    arr[L++] = (tmp >> 16) & 0xFF
    arr[L++] = (tmp >> 8) & 0xFF
    arr[L++] = tmp & 0xFF
  }

  if (placeHolders === 2) {
    tmp = (revLookup[b64.charCodeAt(i)] << 2) | (revLookup[b64.charCodeAt(i + 1)] >> 4)
    arr[L++] = tmp & 0xFF
  } else if (placeHolders === 1) {
    tmp = (revLookup[b64.charCodeAt(i)] << 10) | (revLookup[b64.charCodeAt(i + 1)] << 4) | (revLookup[b64.charCodeAt(i + 2)] >> 2)
    arr[L++] = (tmp >> 8) & 0xFF
    arr[L++] = tmp & 0xFF
  }

  return arr
}

function tripletToBase64 (num) {
  return lookup[num >> 18 & 0x3F] + lookup[num >> 12 & 0x3F] + lookup[num >> 6 & 0x3F] + lookup[num & 0x3F]
}

function encodeChunk (uint8, start, end) {
  var tmp
  var output = []
  for (var i = start; i < end; i += 3) {
    tmp = (uint8[i] << 16) + (uint8[i + 1] << 8) + (uint8[i + 2])
    output.push(tripletToBase64(tmp))
  }
  return output.join('')
}

function fromByteArray (uint8) {
  var tmp
  var len = uint8.length
  var extraBytes = len % 3 // if we have 1 byte left, pad 2 bytes
  var output = ''
  var parts = []
  var maxChunkLength = 16383 // must be multiple of 3

  // go through the array every three bytes, we'll deal with trailing stuff later
  for (var i = 0, len2 = len - extraBytes; i < len2; i += maxChunkLength) {
    parts.push(encodeChunk(uint8, i, (i + maxChunkLength) > len2 ? len2 : (i + maxChunkLength)))
  }

  // pad the end with zeros, but make sure to not forget the extra bytes
  if (extraBytes === 1) {
    tmp = uint8[len - 1]
    output += lookup[tmp >> 2]
    output += lookup[(tmp << 4) & 0x3F]
    output += '=='
  } else if (extraBytes === 2) {
    tmp = (uint8[len - 2] << 8) + (uint8[len - 1])
    output += lookup[tmp >> 10]
    output += lookup[(tmp >> 4) & 0x3F]
    output += lookup[(tmp << 2) & 0x3F]
    output += '='
  }

  parts.push(output)

  return parts.join('')
}


/***/ }),

/***/ 282:
/***/ (function(module, exports, __webpack_require__) {

"use strict";
/* WEBPACK VAR INJECTION */(function(global) {/*!
 * The buffer module from node.js, for the browser.
 *
 * @author   Feross Aboukhadijeh <feross@feross.org> <http://feross.org>
 * @license  MIT
 */
/* eslint-disable no-proto */



var base64 = __webpack_require__(281)
var ieee754 = __webpack_require__(302)
var isArray = __webpack_require__(303)

exports.Buffer = Buffer
exports.SlowBuffer = SlowBuffer
exports.INSPECT_MAX_BYTES = 50

/**
 * If `Buffer.TYPED_ARRAY_SUPPORT`:
 *   === true    Use Uint8Array implementation (fastest)
 *   === false   Use Object implementation (most compatible, even IE6)
 *
 * Browsers that support typed arrays are IE 10+, Firefox 4+, Chrome 7+, Safari 5.1+,
 * Opera 11.6+, iOS 4.2+.
 *
 * Due to various browser bugs, sometimes the Object implementation will be used even
 * when the browser supports typed arrays.
 *
 * Note:
 *
 *   - Firefox 4-29 lacks support for adding new properties to `Uint8Array` instances,
 *     See: https://bugzilla.mozilla.org/show_bug.cgi?id=695438.
 *
 *   - Chrome 9-10 is missing the `TypedArray.prototype.subarray` function.
 *
 *   - IE10 has a broken `TypedArray.prototype.subarray` function which returns arrays of
 *     incorrect length in some situations.

 * We detect these buggy browsers and set `Buffer.TYPED_ARRAY_SUPPORT` to `false` so they
 * get the Object implementation, which is slower but behaves correctly.
 */
Buffer.TYPED_ARRAY_SUPPORT = global.TYPED_ARRAY_SUPPORT !== undefined
  ? global.TYPED_ARRAY_SUPPORT
  : typedArraySupport()

/*
 * Export kMaxLength after typed array support is determined.
 */
exports.kMaxLength = kMaxLength()

function typedArraySupport () {
  try {
    var arr = new Uint8Array(1)
    arr.__proto__ = {__proto__: Uint8Array.prototype, foo: function () { return 42 }}
    return arr.foo() === 42 && // typed array instances can be augmented
        typeof arr.subarray === 'function' && // chrome 9-10 lack `subarray`
        arr.subarray(1, 1).byteLength === 0 // ie10 has broken `subarray`
  } catch (e) {
    return false
  }
}

function kMaxLength () {
  return Buffer.TYPED_ARRAY_SUPPORT
    ? 0x7fffffff
    : 0x3fffffff
}

function createBuffer (that, length) {
  if (kMaxLength() < length) {
    throw new RangeError('Invalid typed array length')
  }
  if (Buffer.TYPED_ARRAY_SUPPORT) {
    // Return an augmented `Uint8Array` instance, for best performance
    that = new Uint8Array(length)
    that.__proto__ = Buffer.prototype
  } else {
    // Fallback: Return an object instance of the Buffer class
    if (that === null) {
      that = new Buffer(length)
    }
    that.length = length
  }

  return that
}

/**
 * The Buffer constructor returns instances of `Uint8Array` that have their
 * prototype changed to `Buffer.prototype`. Furthermore, `Buffer` is a subclass of
 * `Uint8Array`, so the returned instances will have all the node `Buffer` methods
 * and the `Uint8Array` methods. Square bracket notation works as expected -- it
 * returns a single octet.
 *
 * The `Uint8Array` prototype remains unmodified.
 */

function Buffer (arg, encodingOrOffset, length) {
  if (!Buffer.TYPED_ARRAY_SUPPORT && !(this instanceof Buffer)) {
    return new Buffer(arg, encodingOrOffset, length)
  }

  // Common case.
  if (typeof arg === 'number') {
    if (typeof encodingOrOffset === 'string') {
      throw new Error(
        'If encoding is specified then the first argument must be a string'
      )
    }
    return allocUnsafe(this, arg)
  }
  return from(this, arg, encodingOrOffset, length)
}

Buffer.poolSize = 8192 // not used by this implementation

// TODO: Legacy, not needed anymore. Remove in next major version.
Buffer._augment = function (arr) {
  arr.__proto__ = Buffer.prototype
  return arr
}

function from (that, value, encodingOrOffset, length) {
  if (typeof value === 'number') {
    throw new TypeError('"value" argument must not be a number')
  }

  if (typeof ArrayBuffer !== 'undefined' && value instanceof ArrayBuffer) {
    return fromArrayBuffer(that, value, encodingOrOffset, length)
  }

  if (typeof value === 'string') {
    return fromString(that, value, encodingOrOffset)
  }

  return fromObject(that, value)
}

/**
 * Functionally equivalent to Buffer(arg, encoding) but throws a TypeError
 * if value is a number.
 * Buffer.from(str[, encoding])
 * Buffer.from(array)
 * Buffer.from(buffer)
 * Buffer.from(arrayBuffer[, byteOffset[, length]])
 **/
Buffer.from = function (value, encodingOrOffset, length) {
  return from(null, value, encodingOrOffset, length)
}

if (Buffer.TYPED_ARRAY_SUPPORT) {
  Buffer.prototype.__proto__ = Uint8Array.prototype
  Buffer.__proto__ = Uint8Array
  if (typeof Symbol !== 'undefined' && Symbol.species &&
      Buffer[Symbol.species] === Buffer) {
    // Fix subarray() in ES2016. See: https://github.com/feross/buffer/pull/97
    Object.defineProperty(Buffer, Symbol.species, {
      value: null,
      configurable: true
    })
  }
}

function assertSize (size) {
  if (typeof size !== 'number') {
    throw new TypeError('"size" argument must be a number')
  } else if (size < 0) {
    throw new RangeError('"size" argument must not be negative')
  }
}

function alloc (that, size, fill, encoding) {
  assertSize(size)
  if (size <= 0) {
    return createBuffer(that, size)
  }
  if (fill !== undefined) {
    // Only pay attention to encoding if it's a string. This
    // prevents accidentally sending in a number that would
    // be interpretted as a start offset.
    return typeof encoding === 'string'
      ? createBuffer(that, size).fill(fill, encoding)
      : createBuffer(that, size).fill(fill)
  }
  return createBuffer(that, size)
}

/**
 * Creates a new filled Buffer instance.
 * alloc(size[, fill[, encoding]])
 **/
Buffer.alloc = function (size, fill, encoding) {
  return alloc(null, size, fill, encoding)
}

function allocUnsafe (that, size) {
  assertSize(size)
  that = createBuffer(that, size < 0 ? 0 : checked(size) | 0)
  if (!Buffer.TYPED_ARRAY_SUPPORT) {
    for (var i = 0; i < size; ++i) {
      that[i] = 0
    }
  }
  return that
}

/**
 * Equivalent to Buffer(num), by default creates a non-zero-filled Buffer instance.
 * */
Buffer.allocUnsafe = function (size) {
  return allocUnsafe(null, size)
}
/**
 * Equivalent to SlowBuffer(num), by default creates a non-zero-filled Buffer instance.
 */
Buffer.allocUnsafeSlow = function (size) {
  return allocUnsafe(null, size)
}

function fromString (that, string, encoding) {
  if (typeof encoding !== 'string' || encoding === '') {
    encoding = 'utf8'
  }

  if (!Buffer.isEncoding(encoding)) {
    throw new TypeError('"encoding" must be a valid string encoding')
  }

  var length = byteLength(string, encoding) | 0
  that = createBuffer(that, length)

  var actual = that.write(string, encoding)

  if (actual !== length) {
    // Writing a hex string, for example, that contains invalid characters will
    // cause everything after the first invalid character to be ignored. (e.g.
    // 'abxxcd' will be treated as 'ab')
    that = that.slice(0, actual)
  }

  return that
}

function fromArrayLike (that, array) {
  var length = array.length < 0 ? 0 : checked(array.length) | 0
  that = createBuffer(that, length)
  for (var i = 0; i < length; i += 1) {
    that[i] = array[i] & 255
  }
  return that
}

function fromArrayBuffer (that, array, byteOffset, length) {
  array.byteLength // this throws if `array` is not a valid ArrayBuffer

  if (byteOffset < 0 || array.byteLength < byteOffset) {
    throw new RangeError('\'offset\' is out of bounds')
  }

  if (array.byteLength < byteOffset + (length || 0)) {
    throw new RangeError('\'length\' is out of bounds')
  }

  if (byteOffset === undefined && length === undefined) {
    array = new Uint8Array(array)
  } else if (length === undefined) {
    array = new Uint8Array(array, byteOffset)
  } else {
    array = new Uint8Array(array, byteOffset, length)
  }

  if (Buffer.TYPED_ARRAY_SUPPORT) {
    // Return an augmented `Uint8Array` instance, for best performance
    that = array
    that.__proto__ = Buffer.prototype
  } else {
    // Fallback: Return an object instance of the Buffer class
    that = fromArrayLike(that, array)
  }
  return that
}

function fromObject (that, obj) {
  if (Buffer.isBuffer(obj)) {
    var len = checked(obj.length) | 0
    that = createBuffer(that, len)

    if (that.length === 0) {
      return that
    }

    obj.copy(that, 0, 0, len)
    return that
  }

  if (obj) {
    if ((typeof ArrayBuffer !== 'undefined' &&
        obj.buffer instanceof ArrayBuffer) || 'length' in obj) {
      if (typeof obj.length !== 'number' || isnan(obj.length)) {
        return createBuffer(that, 0)
      }
      return fromArrayLike(that, obj)
    }

    if (obj.type === 'Buffer' && isArray(obj.data)) {
      return fromArrayLike(that, obj.data)
    }
  }

  throw new TypeError('First argument must be a string, Buffer, ArrayBuffer, Array, or array-like object.')
}

function checked (length) {
  // Note: cannot use `length < kMaxLength()` here because that fails when
  // length is NaN (which is otherwise coerced to zero.)
  if (length >= kMaxLength()) {
    throw new RangeError('Attempt to allocate Buffer larger than maximum ' +
                         'size: 0x' + kMaxLength().toString(16) + ' bytes')
  }
  return length | 0
}

function SlowBuffer (length) {
  if (+length != length) { // eslint-disable-line eqeqeq
    length = 0
  }
  return Buffer.alloc(+length)
}

Buffer.isBuffer = function isBuffer (b) {
  return !!(b != null && b._isBuffer)
}

Buffer.compare = function compare (a, b) {
  if (!Buffer.isBuffer(a) || !Buffer.isBuffer(b)) {
    throw new TypeError('Arguments must be Buffers')
  }

  if (a === b) return 0

  var x = a.length
  var y = b.length

  for (var i = 0, len = Math.min(x, y); i < len; ++i) {
    if (a[i] !== b[i]) {
      x = a[i]
      y = b[i]
      break
    }
  }

  if (x < y) return -1
  if (y < x) return 1
  return 0
}

Buffer.isEncoding = function isEncoding (encoding) {
  switch (String(encoding).toLowerCase()) {
    case 'hex':
    case 'utf8':
    case 'utf-8':
    case 'ascii':
    case 'latin1':
    case 'binary':
    case 'base64':
    case 'ucs2':
    case 'ucs-2':
    case 'utf16le':
    case 'utf-16le':
      return true
    default:
      return false
  }
}

Buffer.concat = function concat (list, length) {
  if (!isArray(list)) {
    throw new TypeError('"list" argument must be an Array of Buffers')
  }

  if (list.length === 0) {
    return Buffer.alloc(0)
  }

  var i
  if (length === undefined) {
    length = 0
    for (i = 0; i < list.length; ++i) {
      length += list[i].length
    }
  }

  var buffer = Buffer.allocUnsafe(length)
  var pos = 0
  for (i = 0; i < list.length; ++i) {
    var buf = list[i]
    if (!Buffer.isBuffer(buf)) {
      throw new TypeError('"list" argument must be an Array of Buffers')
    }
    buf.copy(buffer, pos)
    pos += buf.length
  }
  return buffer
}

function byteLength (string, encoding) {
  if (Buffer.isBuffer(string)) {
    return string.length
  }
  if (typeof ArrayBuffer !== 'undefined' && typeof ArrayBuffer.isView === 'function' &&
      (ArrayBuffer.isView(string) || string instanceof ArrayBuffer)) {
    return string.byteLength
  }
  if (typeof string !== 'string') {
    string = '' + string
  }

  var len = string.length
  if (len === 0) return 0

  // Use a for loop to avoid recursion
  var loweredCase = false
  for (;;) {
    switch (encoding) {
      case 'ascii':
      case 'latin1':
      case 'binary':
        return len
      case 'utf8':
      case 'utf-8':
      case undefined:
        return utf8ToBytes(string).length
      case 'ucs2':
      case 'ucs-2':
      case 'utf16le':
      case 'utf-16le':
        return len * 2
      case 'hex':
        return len >>> 1
      case 'base64':
        return base64ToBytes(string).length
      default:
        if (loweredCase) return utf8ToBytes(string).length // assume utf8
        encoding = ('' + encoding).toLowerCase()
        loweredCase = true
    }
  }
}
Buffer.byteLength = byteLength

function slowToString (encoding, start, end) {
  var loweredCase = false

  // No need to verify that "this.length <= MAX_UINT32" since it's a read-only
  // property of a typed array.

  // This behaves neither like String nor Uint8Array in that we set start/end
  // to their upper/lower bounds if the value passed is out of range.
  // undefined is handled specially as per ECMA-262 6th Edition,
  // Section 13.3.3.7 Runtime Semantics: KeyedBindingInitialization.
  if (start === undefined || start < 0) {
    start = 0
  }
  // Return early if start > this.length. Done here to prevent potential uint32
  // coercion fail below.
  if (start > this.length) {
    return ''
  }

  if (end === undefined || end > this.length) {
    end = this.length
  }

  if (end <= 0) {
    return ''
  }

  // Force coersion to uint32. This will also coerce falsey/NaN values to 0.
  end >>>= 0
  start >>>= 0

  if (end <= start) {
    return ''
  }

  if (!encoding) encoding = 'utf8'

  while (true) {
    switch (encoding) {
      case 'hex':
        return hexSlice(this, start, end)

      case 'utf8':
      case 'utf-8':
        return utf8Slice(this, start, end)

      case 'ascii':
        return asciiSlice(this, start, end)

      case 'latin1':
      case 'binary':
        return latin1Slice(this, start, end)

      case 'base64':
        return base64Slice(this, start, end)

      case 'ucs2':
      case 'ucs-2':
      case 'utf16le':
      case 'utf-16le':
        return utf16leSlice(this, start, end)

      default:
        if (loweredCase) throw new TypeError('Unknown encoding: ' + encoding)
        encoding = (encoding + '').toLowerCase()
        loweredCase = true
    }
  }
}

// The property is used by `Buffer.isBuffer` and `is-buffer` (in Safari 5-7) to detect
// Buffer instances.
Buffer.prototype._isBuffer = true

function swap (b, n, m) {
  var i = b[n]
  b[n] = b[m]
  b[m] = i
}

Buffer.prototype.swap16 = function swap16 () {
  var len = this.length
  if (len % 2 !== 0) {
    throw new RangeError('Buffer size must be a multiple of 16-bits')
  }
  for (var i = 0; i < len; i += 2) {
    swap(this, i, i + 1)
  }
  return this
}

Buffer.prototype.swap32 = function swap32 () {
  var len = this.length
  if (len % 4 !== 0) {
    throw new RangeError('Buffer size must be a multiple of 32-bits')
  }
  for (var i = 0; i < len; i += 4) {
    swap(this, i, i + 3)
    swap(this, i + 1, i + 2)
  }
  return this
}

Buffer.prototype.swap64 = function swap64 () {
  var len = this.length
  if (len % 8 !== 0) {
    throw new RangeError('Buffer size must be a multiple of 64-bits')
  }
  for (var i = 0; i < len; i += 8) {
    swap(this, i, i + 7)
    swap(this, i + 1, i + 6)
    swap(this, i + 2, i + 5)
    swap(this, i + 3, i + 4)
  }
  return this
}

Buffer.prototype.toString = function toString () {
  var length = this.length | 0
  if (length === 0) return ''
  if (arguments.length === 0) return utf8Slice(this, 0, length)
  return slowToString.apply(this, arguments)
}

Buffer.prototype.equals = function equals (b) {
  if (!Buffer.isBuffer(b)) throw new TypeError('Argument must be a Buffer')
  if (this === b) return true
  return Buffer.compare(this, b) === 0
}

Buffer.prototype.inspect = function inspect () {
  var str = ''
  var max = exports.INSPECT_MAX_BYTES
  if (this.length > 0) {
    str = this.toString('hex', 0, max).match(/.{2}/g).join(' ')
    if (this.length > max) str += ' ... '
  }
  return '<Buffer ' + str + '>'
}

Buffer.prototype.compare = function compare (target, start, end, thisStart, thisEnd) {
  if (!Buffer.isBuffer(target)) {
    throw new TypeError('Argument must be a Buffer')
  }

  if (start === undefined) {
    start = 0
  }
  if (end === undefined) {
    end = target ? target.length : 0
  }
  if (thisStart === undefined) {
    thisStart = 0
  }
  if (thisEnd === undefined) {
    thisEnd = this.length
  }

  if (start < 0 || end > target.length || thisStart < 0 || thisEnd > this.length) {
    throw new RangeError('out of range index')
  }

  if (thisStart >= thisEnd && start >= end) {
    return 0
  }
  if (thisStart >= thisEnd) {
    return -1
  }
  if (start >= end) {
    return 1
  }

  start >>>= 0
  end >>>= 0
  thisStart >>>= 0
  thisEnd >>>= 0

  if (this === target) return 0

  var x = thisEnd - thisStart
  var y = end - start
  var len = Math.min(x, y)

  var thisCopy = this.slice(thisStart, thisEnd)
  var targetCopy = target.slice(start, end)

  for (var i = 0; i < len; ++i) {
    if (thisCopy[i] !== targetCopy[i]) {
      x = thisCopy[i]
      y = targetCopy[i]
      break
    }
  }

  if (x < y) return -1
  if (y < x) return 1
  return 0
}

// Finds either the first index of `val` in `buffer` at offset >= `byteOffset`,
// OR the last index of `val` in `buffer` at offset <= `byteOffset`.
//
// Arguments:
// - buffer - a Buffer to search
// - val - a string, Buffer, or number
// - byteOffset - an index into `buffer`; will be clamped to an int32
// - encoding - an optional encoding, relevant is val is a string
// - dir - true for indexOf, false for lastIndexOf
function bidirectionalIndexOf (buffer, val, byteOffset, encoding, dir) {
  // Empty buffer means no match
  if (buffer.length === 0) return -1

  // Normalize byteOffset
  if (typeof byteOffset === 'string') {
    encoding = byteOffset
    byteOffset = 0
  } else if (byteOffset > 0x7fffffff) {
    byteOffset = 0x7fffffff
  } else if (byteOffset < -0x80000000) {
    byteOffset = -0x80000000
  }
  byteOffset = +byteOffset  // Coerce to Number.
  if (isNaN(byteOffset)) {
    // byteOffset: it it's undefined, null, NaN, "foo", etc, search whole buffer
    byteOffset = dir ? 0 : (buffer.length - 1)
  }

  // Normalize byteOffset: negative offsets start from the end of the buffer
  if (byteOffset < 0) byteOffset = buffer.length + byteOffset
  if (byteOffset >= buffer.length) {
    if (dir) return -1
    else byteOffset = buffer.length - 1
  } else if (byteOffset < 0) {
    if (dir) byteOffset = 0
    else return -1
  }

  // Normalize val
  if (typeof val === 'string') {
    val = Buffer.from(val, encoding)
  }

  // Finally, search either indexOf (if dir is true) or lastIndexOf
  if (Buffer.isBuffer(val)) {
    // Special case: looking for empty string/buffer always fails
    if (val.length === 0) {
      return -1
    }
    return arrayIndexOf(buffer, val, byteOffset, encoding, dir)
  } else if (typeof val === 'number') {
    val = val & 0xFF // Search for a byte value [0-255]
    if (Buffer.TYPED_ARRAY_SUPPORT &&
        typeof Uint8Array.prototype.indexOf === 'function') {
      if (dir) {
        return Uint8Array.prototype.indexOf.call(buffer, val, byteOffset)
      } else {
        return Uint8Array.prototype.lastIndexOf.call(buffer, val, byteOffset)
      }
    }
    return arrayIndexOf(buffer, [ val ], byteOffset, encoding, dir)
  }

  throw new TypeError('val must be string, number or Buffer')
}

function arrayIndexOf (arr, val, byteOffset, encoding, dir) {
  var indexSize = 1
  var arrLength = arr.length
  var valLength = val.length

  if (encoding !== undefined) {
    encoding = String(encoding).toLowerCase()
    if (encoding === 'ucs2' || encoding === 'ucs-2' ||
        encoding === 'utf16le' || encoding === 'utf-16le') {
      if (arr.length < 2 || val.length < 2) {
        return -1
      }
      indexSize = 2
      arrLength /= 2
      valLength /= 2
      byteOffset /= 2
    }
  }

  function read (buf, i) {
    if (indexSize === 1) {
      return buf[i]
    } else {
      return buf.readUInt16BE(i * indexSize)
    }
  }

  var i
  if (dir) {
    var foundIndex = -1
    for (i = byteOffset; i < arrLength; i++) {
      if (read(arr, i) === read(val, foundIndex === -1 ? 0 : i - foundIndex)) {
        if (foundIndex === -1) foundIndex = i
        if (i - foundIndex + 1 === valLength) return foundIndex * indexSize
      } else {
        if (foundIndex !== -1) i -= i - foundIndex
        foundIndex = -1
      }
    }
  } else {
    if (byteOffset + valLength > arrLength) byteOffset = arrLength - valLength
    for (i = byteOffset; i >= 0; i--) {
      var found = true
      for (var j = 0; j < valLength; j++) {
        if (read(arr, i + j) !== read(val, j)) {
          found = false
          break
        }
      }
      if (found) return i
    }
  }

  return -1
}

Buffer.prototype.includes = function includes (val, byteOffset, encoding) {
  return this.indexOf(val, byteOffset, encoding) !== -1
}

Buffer.prototype.indexOf = function indexOf (val, byteOffset, encoding) {
  return bidirectionalIndexOf(this, val, byteOffset, encoding, true)
}

Buffer.prototype.lastIndexOf = function lastIndexOf (val, byteOffset, encoding) {
  return bidirectionalIndexOf(this, val, byteOffset, encoding, false)
}

function hexWrite (buf, string, offset, length) {
  offset = Number(offset) || 0
  var remaining = buf.length - offset
  if (!length) {
    length = remaining
  } else {
    length = Number(length)
    if (length > remaining) {
      length = remaining
    }
  }

  // must be an even number of digits
  var strLen = string.length
  if (strLen % 2 !== 0) throw new TypeError('Invalid hex string')

  if (length > strLen / 2) {
    length = strLen / 2
  }
  for (var i = 0; i < length; ++i) {
    var parsed = parseInt(string.substr(i * 2, 2), 16)
    if (isNaN(parsed)) return i
    buf[offset + i] = parsed
  }
  return i
}

function utf8Write (buf, string, offset, length) {
  return blitBuffer(utf8ToBytes(string, buf.length - offset), buf, offset, length)
}

function asciiWrite (buf, string, offset, length) {
  return blitBuffer(asciiToBytes(string), buf, offset, length)
}

function latin1Write (buf, string, offset, length) {
  return asciiWrite(buf, string, offset, length)
}

function base64Write (buf, string, offset, length) {
  return blitBuffer(base64ToBytes(string), buf, offset, length)
}

function ucs2Write (buf, string, offset, length) {
  return blitBuffer(utf16leToBytes(string, buf.length - offset), buf, offset, length)
}

Buffer.prototype.write = function write (string, offset, length, encoding) {
  // Buffer#write(string)
  if (offset === undefined) {
    encoding = 'utf8'
    length = this.length
    offset = 0
  // Buffer#write(string, encoding)
  } else if (length === undefined && typeof offset === 'string') {
    encoding = offset
    length = this.length
    offset = 0
  // Buffer#write(string, offset[, length][, encoding])
  } else if (isFinite(offset)) {
    offset = offset | 0
    if (isFinite(length)) {
      length = length | 0
      if (encoding === undefined) encoding = 'utf8'
    } else {
      encoding = length
      length = undefined
    }
  // legacy write(string, encoding, offset, length) - remove in v0.13
  } else {
    throw new Error(
      'Buffer.write(string, encoding, offset[, length]) is no longer supported'
    )
  }

  var remaining = this.length - offset
  if (length === undefined || length > remaining) length = remaining

  if ((string.length > 0 && (length < 0 || offset < 0)) || offset > this.length) {
    throw new RangeError('Attempt to write outside buffer bounds')
  }

  if (!encoding) encoding = 'utf8'

  var loweredCase = false
  for (;;) {
    switch (encoding) {
      case 'hex':
        return hexWrite(this, string, offset, length)

      case 'utf8':
      case 'utf-8':
        return utf8Write(this, string, offset, length)

      case 'ascii':
        return asciiWrite(this, string, offset, length)

      case 'latin1':
      case 'binary':
        return latin1Write(this, string, offset, length)

      case 'base64':
        // Warning: maxLength not taken into account in base64Write
        return base64Write(this, string, offset, length)

      case 'ucs2':
      case 'ucs-2':
      case 'utf16le':
      case 'utf-16le':
        return ucs2Write(this, string, offset, length)

      default:
        if (loweredCase) throw new TypeError('Unknown encoding: ' + encoding)
        encoding = ('' + encoding).toLowerCase()
        loweredCase = true
    }
  }
}

Buffer.prototype.toJSON = function toJSON () {
  return {
    type: 'Buffer',
    data: Array.prototype.slice.call(this._arr || this, 0)
  }
}

function base64Slice (buf, start, end) {
  if (start === 0 && end === buf.length) {
    return base64.fromByteArray(buf)
  } else {
    return base64.fromByteArray(buf.slice(start, end))
  }
}

function utf8Slice (buf, start, end) {
  end = Math.min(buf.length, end)
  var res = []

  var i = start
  while (i < end) {
    var firstByte = buf[i]
    var codePoint = null
    var bytesPerSequence = (firstByte > 0xEF) ? 4
      : (firstByte > 0xDF) ? 3
      : (firstByte > 0xBF) ? 2
      : 1

    if (i + bytesPerSequence <= end) {
      var secondByte, thirdByte, fourthByte, tempCodePoint

      switch (bytesPerSequence) {
        case 1:
          if (firstByte < 0x80) {
            codePoint = firstByte
          }
          break
        case 2:
          secondByte = buf[i + 1]
          if ((secondByte & 0xC0) === 0x80) {
            tempCodePoint = (firstByte & 0x1F) << 0x6 | (secondByte & 0x3F)
            if (tempCodePoint > 0x7F) {
              codePoint = tempCodePoint
            }
          }
          break
        case 3:
          secondByte = buf[i + 1]
          thirdByte = buf[i + 2]
          if ((secondByte & 0xC0) === 0x80 && (thirdByte & 0xC0) === 0x80) {
            tempCodePoint = (firstByte & 0xF) << 0xC | (secondByte & 0x3F) << 0x6 | (thirdByte & 0x3F)
            if (tempCodePoint > 0x7FF && (tempCodePoint < 0xD800 || tempCodePoint > 0xDFFF)) {
              codePoint = tempCodePoint
            }
          }
          break
        case 4:
          secondByte = buf[i + 1]
          thirdByte = buf[i + 2]
          fourthByte = buf[i + 3]
          if ((secondByte & 0xC0) === 0x80 && (thirdByte & 0xC0) === 0x80 && (fourthByte & 0xC0) === 0x80) {
            tempCodePoint = (firstByte & 0xF) << 0x12 | (secondByte & 0x3F) << 0xC | (thirdByte & 0x3F) << 0x6 | (fourthByte & 0x3F)
            if (tempCodePoint > 0xFFFF && tempCodePoint < 0x110000) {
              codePoint = tempCodePoint
            }
          }
      }
    }

    if (codePoint === null) {
      // we did not generate a valid codePoint so insert a
      // replacement char (U+FFFD) and advance only 1 byte
      codePoint = 0xFFFD
      bytesPerSequence = 1
    } else if (codePoint > 0xFFFF) {
      // encode to utf16 (surrogate pair dance)
      codePoint -= 0x10000
      res.push(codePoint >>> 10 & 0x3FF | 0xD800)
      codePoint = 0xDC00 | codePoint & 0x3FF
    }

    res.push(codePoint)
    i += bytesPerSequence
  }

  return decodeCodePointsArray(res)
}

// Based on http://stackoverflow.com/a/22747272/680742, the browser with
// the lowest limit is Chrome, with 0x10000 args.
// We go 1 magnitude less, for safety
var MAX_ARGUMENTS_LENGTH = 0x1000

function decodeCodePointsArray (codePoints) {
  var len = codePoints.length
  if (len <= MAX_ARGUMENTS_LENGTH) {
    return String.fromCharCode.apply(String, codePoints) // avoid extra slice()
  }

  // Decode in chunks to avoid "call stack size exceeded".
  var res = ''
  var i = 0
  while (i < len) {
    res += String.fromCharCode.apply(
      String,
      codePoints.slice(i, i += MAX_ARGUMENTS_LENGTH)
    )
  }
  return res
}

function asciiSlice (buf, start, end) {
  var ret = ''
  end = Math.min(buf.length, end)

  for (var i = start; i < end; ++i) {
    ret += String.fromCharCode(buf[i] & 0x7F)
  }
  return ret
}

function latin1Slice (buf, start, end) {
  var ret = ''
  end = Math.min(buf.length, end)

  for (var i = start; i < end; ++i) {
    ret += String.fromCharCode(buf[i])
  }
  return ret
}

function hexSlice (buf, start, end) {
  var len = buf.length

  if (!start || start < 0) start = 0
  if (!end || end < 0 || end > len) end = len

  var out = ''
  for (var i = start; i < end; ++i) {
    out += toHex(buf[i])
  }
  return out
}

function utf16leSlice (buf, start, end) {
  var bytes = buf.slice(start, end)
  var res = ''
  for (var i = 0; i < bytes.length; i += 2) {
    res += String.fromCharCode(bytes[i] + bytes[i + 1] * 256)
  }
  return res
}

Buffer.prototype.slice = function slice (start, end) {
  var len = this.length
  start = ~~start
  end = end === undefined ? len : ~~end

  if (start < 0) {
    start += len
    if (start < 0) start = 0
  } else if (start > len) {
    start = len
  }

  if (end < 0) {
    end += len
    if (end < 0) end = 0
  } else if (end > len) {
    end = len
  }

  if (end < start) end = start

  var newBuf
  if (Buffer.TYPED_ARRAY_SUPPORT) {
    newBuf = this.subarray(start, end)
    newBuf.__proto__ = Buffer.prototype
  } else {
    var sliceLen = end - start
    newBuf = new Buffer(sliceLen, undefined)
    for (var i = 0; i < sliceLen; ++i) {
      newBuf[i] = this[i + start]
    }
  }

  return newBuf
}

/*
 * Need to make sure that buffer isn't trying to write out of bounds.
 */
function checkOffset (offset, ext, length) {
  if ((offset % 1) !== 0 || offset < 0) throw new RangeError('offset is not uint')
  if (offset + ext > length) throw new RangeError('Trying to access beyond buffer length')
}

Buffer.prototype.readUIntLE = function readUIntLE (offset, byteLength, noAssert) {
  offset = offset | 0
  byteLength = byteLength | 0
  if (!noAssert) checkOffset(offset, byteLength, this.length)

  var val = this[offset]
  var mul = 1
  var i = 0
  while (++i < byteLength && (mul *= 0x100)) {
    val += this[offset + i] * mul
  }

  return val
}

Buffer.prototype.readUIntBE = function readUIntBE (offset, byteLength, noAssert) {
  offset = offset | 0
  byteLength = byteLength | 0
  if (!noAssert) {
    checkOffset(offset, byteLength, this.length)
  }

  var val = this[offset + --byteLength]
  var mul = 1
  while (byteLength > 0 && (mul *= 0x100)) {
    val += this[offset + --byteLength] * mul
  }

  return val
}

Buffer.prototype.readUInt8 = function readUInt8 (offset, noAssert) {
  if (!noAssert) checkOffset(offset, 1, this.length)
  return this[offset]
}

Buffer.prototype.readUInt16LE = function readUInt16LE (offset, noAssert) {
  if (!noAssert) checkOffset(offset, 2, this.length)
  return this[offset] | (this[offset + 1] << 8)
}

Buffer.prototype.readUInt16BE = function readUInt16BE (offset, noAssert) {
  if (!noAssert) checkOffset(offset, 2, this.length)
  return (this[offset] << 8) | this[offset + 1]
}

Buffer.prototype.readUInt32LE = function readUInt32LE (offset, noAssert) {
  if (!noAssert) checkOffset(offset, 4, this.length)

  return ((this[offset]) |
      (this[offset + 1] << 8) |
      (this[offset + 2] << 16)) +
      (this[offset + 3] * 0x1000000)
}

Buffer.prototype.readUInt32BE = function readUInt32BE (offset, noAssert) {
  if (!noAssert) checkOffset(offset, 4, this.length)

  return (this[offset] * 0x1000000) +
    ((this[offset + 1] << 16) |
    (this[offset + 2] << 8) |
    this[offset + 3])
}

Buffer.prototype.readIntLE = function readIntLE (offset, byteLength, noAssert) {
  offset = offset | 0
  byteLength = byteLength | 0
  if (!noAssert) checkOffset(offset, byteLength, this.length)

  var val = this[offset]
  var mul = 1
  var i = 0
  while (++i < byteLength && (mul *= 0x100)) {
    val += this[offset + i] * mul
  }
  mul *= 0x80

  if (val >= mul) val -= Math.pow(2, 8 * byteLength)

  return val
}

Buffer.prototype.readIntBE = function readIntBE (offset, byteLength, noAssert) {
  offset = offset | 0
  byteLength = byteLength | 0
  if (!noAssert) checkOffset(offset, byteLength, this.length)

  var i = byteLength
  var mul = 1
  var val = this[offset + --i]
  while (i > 0 && (mul *= 0x100)) {
    val += this[offset + --i] * mul
  }
  mul *= 0x80

  if (val >= mul) val -= Math.pow(2, 8 * byteLength)

  return val
}

Buffer.prototype.readInt8 = function readInt8 (offset, noAssert) {
  if (!noAssert) checkOffset(offset, 1, this.length)
  if (!(this[offset] & 0x80)) return (this[offset])
  return ((0xff - this[offset] + 1) * -1)
}

Buffer.prototype.readInt16LE = function readInt16LE (offset, noAssert) {
  if (!noAssert) checkOffset(offset, 2, this.length)
  var val = this[offset] | (this[offset + 1] << 8)
  return (val & 0x8000) ? val | 0xFFFF0000 : val
}

Buffer.prototype.readInt16BE = function readInt16BE (offset, noAssert) {
  if (!noAssert) checkOffset(offset, 2, this.length)
  var val = this[offset + 1] | (this[offset] << 8)
  return (val & 0x8000) ? val | 0xFFFF0000 : val
}

Buffer.prototype.readInt32LE = function readInt32LE (offset, noAssert) {
  if (!noAssert) checkOffset(offset, 4, this.length)

  return (this[offset]) |
    (this[offset + 1] << 8) |
    (this[offset + 2] << 16) |
    (this[offset + 3] << 24)
}

Buffer.prototype.readInt32BE = function readInt32BE (offset, noAssert) {
  if (!noAssert) checkOffset(offset, 4, this.length)

  return (this[offset] << 24) |
    (this[offset + 1] << 16) |
    (this[offset + 2] << 8) |
    (this[offset + 3])
}

Buffer.prototype.readFloatLE = function readFloatLE (offset, noAssert) {
  if (!noAssert) checkOffset(offset, 4, this.length)
  return ieee754.read(this, offset, true, 23, 4)
}

Buffer.prototype.readFloatBE = function readFloatBE (offset, noAssert) {
  if (!noAssert) checkOffset(offset, 4, this.length)
  return ieee754.read(this, offset, false, 23, 4)
}

Buffer.prototype.readDoubleLE = function readDoubleLE (offset, noAssert) {
  if (!noAssert) checkOffset(offset, 8, this.length)
  return ieee754.read(this, offset, true, 52, 8)
}

Buffer.prototype.readDoubleBE = function readDoubleBE (offset, noAssert) {
  if (!noAssert) checkOffset(offset, 8, this.length)
  return ieee754.read(this, offset, false, 52, 8)
}

function checkInt (buf, value, offset, ext, max, min) {
  if (!Buffer.isBuffer(buf)) throw new TypeError('"buffer" argument must be a Buffer instance')
  if (value > max || value < min) throw new RangeError('"value" argument is out of bounds')
  if (offset + ext > buf.length) throw new RangeError('Index out of range')
}

Buffer.prototype.writeUIntLE = function writeUIntLE (value, offset, byteLength, noAssert) {
  value = +value
  offset = offset | 0
  byteLength = byteLength | 0
  if (!noAssert) {
    var maxBytes = Math.pow(2, 8 * byteLength) - 1
    checkInt(this, value, offset, byteLength, maxBytes, 0)
  }

  var mul = 1
  var i = 0
  this[offset] = value & 0xFF
  while (++i < byteLength && (mul *= 0x100)) {
    this[offset + i] = (value / mul) & 0xFF
  }

  return offset + byteLength
}

Buffer.prototype.writeUIntBE = function writeUIntBE (value, offset, byteLength, noAssert) {
  value = +value
  offset = offset | 0
  byteLength = byteLength | 0
  if (!noAssert) {
    var maxBytes = Math.pow(2, 8 * byteLength) - 1
    checkInt(this, value, offset, byteLength, maxBytes, 0)
  }

  var i = byteLength - 1
  var mul = 1
  this[offset + i] = value & 0xFF
  while (--i >= 0 && (mul *= 0x100)) {
    this[offset + i] = (value / mul) & 0xFF
  }

  return offset + byteLength
}

Buffer.prototype.writeUInt8 = function writeUInt8 (value, offset, noAssert) {
  value = +value
  offset = offset | 0
  if (!noAssert) checkInt(this, value, offset, 1, 0xff, 0)
  if (!Buffer.TYPED_ARRAY_SUPPORT) value = Math.floor(value)
  this[offset] = (value & 0xff)
  return offset + 1
}

function objectWriteUInt16 (buf, value, offset, littleEndian) {
  if (value < 0) value = 0xffff + value + 1
  for (var i = 0, j = Math.min(buf.length - offset, 2); i < j; ++i) {
    buf[offset + i] = (value & (0xff << (8 * (littleEndian ? i : 1 - i)))) >>>
      (littleEndian ? i : 1 - i) * 8
  }
}

Buffer.prototype.writeUInt16LE = function writeUInt16LE (value, offset, noAssert) {
  value = +value
  offset = offset | 0
  if (!noAssert) checkInt(this, value, offset, 2, 0xffff, 0)
  if (Buffer.TYPED_ARRAY_SUPPORT) {
    this[offset] = (value & 0xff)
    this[offset + 1] = (value >>> 8)
  } else {
    objectWriteUInt16(this, value, offset, true)
  }
  return offset + 2
}

Buffer.prototype.writeUInt16BE = function writeUInt16BE (value, offset, noAssert) {
  value = +value
  offset = offset | 0
  if (!noAssert) checkInt(this, value, offset, 2, 0xffff, 0)
  if (Buffer.TYPED_ARRAY_SUPPORT) {
    this[offset] = (value >>> 8)
    this[offset + 1] = (value & 0xff)
  } else {
    objectWriteUInt16(this, value, offset, false)
  }
  return offset + 2
}

function objectWriteUInt32 (buf, value, offset, littleEndian) {
  if (value < 0) value = 0xffffffff + value + 1
  for (var i = 0, j = Math.min(buf.length - offset, 4); i < j; ++i) {
    buf[offset + i] = (value >>> (littleEndian ? i : 3 - i) * 8) & 0xff
  }
}

Buffer.prototype.writeUInt32LE = function writeUInt32LE (value, offset, noAssert) {
  value = +value
  offset = offset | 0
  if (!noAssert) checkInt(this, value, offset, 4, 0xffffffff, 0)
  if (Buffer.TYPED_ARRAY_SUPPORT) {
    this[offset + 3] = (value >>> 24)
    this[offset + 2] = (value >>> 16)
    this[offset + 1] = (value >>> 8)
    this[offset] = (value & 0xff)
  } else {
    objectWriteUInt32(this, value, offset, true)
  }
  return offset + 4
}

Buffer.prototype.writeUInt32BE = function writeUInt32BE (value, offset, noAssert) {
  value = +value
  offset = offset | 0
  if (!noAssert) checkInt(this, value, offset, 4, 0xffffffff, 0)
  if (Buffer.TYPED_ARRAY_SUPPORT) {
    this[offset] = (value >>> 24)
    this[offset + 1] = (value >>> 16)
    this[offset + 2] = (value >>> 8)
    this[offset + 3] = (value & 0xff)
  } else {
    objectWriteUInt32(this, value, offset, false)
  }
  return offset + 4
}

Buffer.prototype.writeIntLE = function writeIntLE (value, offset, byteLength, noAssert) {
  value = +value
  offset = offset | 0
  if (!noAssert) {
    var limit = Math.pow(2, 8 * byteLength - 1)

    checkInt(this, value, offset, byteLength, limit - 1, -limit)
  }

  var i = 0
  var mul = 1
  var sub = 0
  this[offset] = value & 0xFF
  while (++i < byteLength && (mul *= 0x100)) {
    if (value < 0 && sub === 0 && this[offset + i - 1] !== 0) {
      sub = 1
    }
    this[offset + i] = ((value / mul) >> 0) - sub & 0xFF
  }

  return offset + byteLength
}

Buffer.prototype.writeIntBE = function writeIntBE (value, offset, byteLength, noAssert) {
  value = +value
  offset = offset | 0
  if (!noAssert) {
    var limit = Math.pow(2, 8 * byteLength - 1)

    checkInt(this, value, offset, byteLength, limit - 1, -limit)
  }

  var i = byteLength - 1
  var mul = 1
  var sub = 0
  this[offset + i] = value & 0xFF
  while (--i >= 0 && (mul *= 0x100)) {
    if (value < 0 && sub === 0 && this[offset + i + 1] !== 0) {
      sub = 1
    }
    this[offset + i] = ((value / mul) >> 0) - sub & 0xFF
  }

  return offset + byteLength
}

Buffer.prototype.writeInt8 = function writeInt8 (value, offset, noAssert) {
  value = +value
  offset = offset | 0
  if (!noAssert) checkInt(this, value, offset, 1, 0x7f, -0x80)
  if (!Buffer.TYPED_ARRAY_SUPPORT) value = Math.floor(value)
  if (value < 0) value = 0xff + value + 1
  this[offset] = (value & 0xff)
  return offset + 1
}

Buffer.prototype.writeInt16LE = function writeInt16LE (value, offset, noAssert) {
  value = +value
  offset = offset | 0
  if (!noAssert) checkInt(this, value, offset, 2, 0x7fff, -0x8000)
  if (Buffer.TYPED_ARRAY_SUPPORT) {
    this[offset] = (value & 0xff)
    this[offset + 1] = (value >>> 8)
  } else {
    objectWriteUInt16(this, value, offset, true)
  }
  return offset + 2
}

Buffer.prototype.writeInt16BE = function writeInt16BE (value, offset, noAssert) {
  value = +value
  offset = offset | 0
  if (!noAssert) checkInt(this, value, offset, 2, 0x7fff, -0x8000)
  if (Buffer.TYPED_ARRAY_SUPPORT) {
    this[offset] = (value >>> 8)
    this[offset + 1] = (value & 0xff)
  } else {
    objectWriteUInt16(this, value, offset, false)
  }
  return offset + 2
}

Buffer.prototype.writeInt32LE = function writeInt32LE (value, offset, noAssert) {
  value = +value
  offset = offset | 0
  if (!noAssert) checkInt(this, value, offset, 4, 0x7fffffff, -0x80000000)
  if (Buffer.TYPED_ARRAY_SUPPORT) {
    this[offset] = (value & 0xff)
    this[offset + 1] = (value >>> 8)
    this[offset + 2] = (value >>> 16)
    this[offset + 3] = (value >>> 24)
  } else {
    objectWriteUInt32(this, value, offset, true)
  }
  return offset + 4
}

Buffer.prototype.writeInt32BE = function writeInt32BE (value, offset, noAssert) {
  value = +value
  offset = offset | 0
  if (!noAssert) checkInt(this, value, offset, 4, 0x7fffffff, -0x80000000)
  if (value < 0) value = 0xffffffff + value + 1
  if (Buffer.TYPED_ARRAY_SUPPORT) {
    this[offset] = (value >>> 24)
    this[offset + 1] = (value >>> 16)
    this[offset + 2] = (value >>> 8)
    this[offset + 3] = (value & 0xff)
  } else {
    objectWriteUInt32(this, value, offset, false)
  }
  return offset + 4
}

function checkIEEE754 (buf, value, offset, ext, max, min) {
  if (offset + ext > buf.length) throw new RangeError('Index out of range')
  if (offset < 0) throw new RangeError('Index out of range')
}

function writeFloat (buf, value, offset, littleEndian, noAssert) {
  if (!noAssert) {
    checkIEEE754(buf, value, offset, 4, 3.4028234663852886e+38, -3.4028234663852886e+38)
  }
  ieee754.write(buf, value, offset, littleEndian, 23, 4)
  return offset + 4
}

Buffer.prototype.writeFloatLE = function writeFloatLE (value, offset, noAssert) {
  return writeFloat(this, value, offset, true, noAssert)
}

Buffer.prototype.writeFloatBE = function writeFloatBE (value, offset, noAssert) {
  return writeFloat(this, value, offset, false, noAssert)
}

function writeDouble (buf, value, offset, littleEndian, noAssert) {
  if (!noAssert) {
    checkIEEE754(buf, value, offset, 8, 1.7976931348623157E+308, -1.7976931348623157E+308)
  }
  ieee754.write(buf, value, offset, littleEndian, 52, 8)
  return offset + 8
}

Buffer.prototype.writeDoubleLE = function writeDoubleLE (value, offset, noAssert) {
  return writeDouble(this, value, offset, true, noAssert)
}

Buffer.prototype.writeDoubleBE = function writeDoubleBE (value, offset, noAssert) {
  return writeDouble(this, value, offset, false, noAssert)
}

// copy(targetBuffer, targetStart=0, sourceStart=0, sourceEnd=buffer.length)
Buffer.prototype.copy = function copy (target, targetStart, start, end) {
  if (!start) start = 0
  if (!end && end !== 0) end = this.length
  if (targetStart >= target.length) targetStart = target.length
  if (!targetStart) targetStart = 0
  if (end > 0 && end < start) end = start

  // Copy 0 bytes; we're done
  if (end === start) return 0
  if (target.length === 0 || this.length === 0) return 0

  // Fatal error conditions
  if (targetStart < 0) {
    throw new RangeError('targetStart out of bounds')
  }
  if (start < 0 || start >= this.length) throw new RangeError('sourceStart out of bounds')
  if (end < 0) throw new RangeError('sourceEnd out of bounds')

  // Are we oob?
  if (end > this.length) end = this.length
  if (target.length - targetStart < end - start) {
    end = target.length - targetStart + start
  }

  var len = end - start
  var i

  if (this === target && start < targetStart && targetStart < end) {
    // descending copy from end
    for (i = len - 1; i >= 0; --i) {
      target[i + targetStart] = this[i + start]
    }
  } else if (len < 1000 || !Buffer.TYPED_ARRAY_SUPPORT) {
    // ascending copy from start
    for (i = 0; i < len; ++i) {
      target[i + targetStart] = this[i + start]
    }
  } else {
    Uint8Array.prototype.set.call(
      target,
      this.subarray(start, start + len),
      targetStart
    )
  }

  return len
}

// Usage:
//    buffer.fill(number[, offset[, end]])
//    buffer.fill(buffer[, offset[, end]])
//    buffer.fill(string[, offset[, end]][, encoding])
Buffer.prototype.fill = function fill (val, start, end, encoding) {
  // Handle string cases:
  if (typeof val === 'string') {
    if (typeof start === 'string') {
      encoding = start
      start = 0
      end = this.length
    } else if (typeof end === 'string') {
      encoding = end
      end = this.length
    }
    if (val.length === 1) {
      var code = val.charCodeAt(0)
      if (code < 256) {
        val = code
      }
    }
    if (encoding !== undefined && typeof encoding !== 'string') {
      throw new TypeError('encoding must be a string')
    }
    if (typeof encoding === 'string' && !Buffer.isEncoding(encoding)) {
      throw new TypeError('Unknown encoding: ' + encoding)
    }
  } else if (typeof val === 'number') {
    val = val & 255
  }

  // Invalid ranges are not set to a default, so can range check early.
  if (start < 0 || this.length < start || this.length < end) {
    throw new RangeError('Out of range index')
  }

  if (end <= start) {
    return this
  }

  start = start >>> 0
  end = end === undefined ? this.length : end >>> 0

  if (!val) val = 0

  var i
  if (typeof val === 'number') {
    for (i = start; i < end; ++i) {
      this[i] = val
    }
  } else {
    var bytes = Buffer.isBuffer(val)
      ? val
      : utf8ToBytes(new Buffer(val, encoding).toString())
    var len = bytes.length
    for (i = 0; i < end - start; ++i) {
      this[i + start] = bytes[i % len]
    }
  }

  return this
}

// HELPER FUNCTIONS
// ================

var INVALID_BASE64_RE = /[^+\/0-9A-Za-z-_]/g

function base64clean (str) {
  // Node strips out invalid characters like \n and \t from the string, base64-js does not
  str = stringtrim(str).replace(INVALID_BASE64_RE, '')
  // Node converts strings with length < 2 to ''
  if (str.length < 2) return ''
  // Node allows for non-padded base64 strings (missing trailing ===), base64-js does not
  while (str.length % 4 !== 0) {
    str = str + '='
  }
  return str
}

function stringtrim (str) {
  if (str.trim) return str.trim()
  return str.replace(/^\s+|\s+$/g, '')
}

function toHex (n) {
  if (n < 16) return '0' + n.toString(16)
  return n.toString(16)
}

function utf8ToBytes (string, units) {
  units = units || Infinity
  var codePoint
  var length = string.length
  var leadSurrogate = null
  var bytes = []

  for (var i = 0; i < length; ++i) {
    codePoint = string.charCodeAt(i)

    // is surrogate component
    if (codePoint > 0xD7FF && codePoint < 0xE000) {
      // last char was a lead
      if (!leadSurrogate) {
        // no lead yet
        if (codePoint > 0xDBFF) {
          // unexpected trail
          if ((units -= 3) > -1) bytes.push(0xEF, 0xBF, 0xBD)
          continue
        } else if (i + 1 === length) {
          // unpaired lead
          if ((units -= 3) > -1) bytes.push(0xEF, 0xBF, 0xBD)
          continue
        }

        // valid lead
        leadSurrogate = codePoint

        continue
      }

      // 2 leads in a row
      if (codePoint < 0xDC00) {
        if ((units -= 3) > -1) bytes.push(0xEF, 0xBF, 0xBD)
        leadSurrogate = codePoint
        continue
      }

      // valid surrogate pair
      codePoint = (leadSurrogate - 0xD800 << 10 | codePoint - 0xDC00) + 0x10000
    } else if (leadSurrogate) {
      // valid bmp char, but last char was a lead
      if ((units -= 3) > -1) bytes.push(0xEF, 0xBF, 0xBD)
    }

    leadSurrogate = null

    // encode utf8
    if (codePoint < 0x80) {
      if ((units -= 1) < 0) break
      bytes.push(codePoint)
    } else if (codePoint < 0x800) {
      if ((units -= 2) < 0) break
      bytes.push(
        codePoint >> 0x6 | 0xC0,
        codePoint & 0x3F | 0x80
      )
    } else if (codePoint < 0x10000) {
      if ((units -= 3) < 0) break
      bytes.push(
        codePoint >> 0xC | 0xE0,
        codePoint >> 0x6 & 0x3F | 0x80,
        codePoint & 0x3F | 0x80
      )
    } else if (codePoint < 0x110000) {
      if ((units -= 4) < 0) break
      bytes.push(
        codePoint >> 0x12 | 0xF0,
        codePoint >> 0xC & 0x3F | 0x80,
        codePoint >> 0x6 & 0x3F | 0x80,
        codePoint & 0x3F | 0x80
      )
    } else {
      throw new Error('Invalid code point')
    }
  }

  return bytes
}

function asciiToBytes (str) {
  var byteArray = []
  for (var i = 0; i < str.length; ++i) {
    // Node's code seems to be doing this and not & 0x7F..
    byteArray.push(str.charCodeAt(i) & 0xFF)
  }
  return byteArray
}

function utf16leToBytes (str, units) {
  var c, hi, lo
  var byteArray = []
  for (var i = 0; i < str.length; ++i) {
    if ((units -= 2) < 0) break

    c = str.charCodeAt(i)
    hi = c >> 8
    lo = c % 256
    byteArray.push(lo)
    byteArray.push(hi)
  }

  return byteArray
}

function base64ToBytes (str) {
  return base64.toByteArray(base64clean(str))
}

function blitBuffer (src, dst, offset, length) {
  for (var i = 0; i < length; ++i) {
    if ((i + offset >= dst.length) || (i >= src.length)) break
    dst[i + offset] = src[i]
  }
  return i
}

function isnan (val) {
  return val !== val // eslint-disable-line no-self-compare
}

/* WEBPACK VAR INJECTION */}.call(exports, __webpack_require__(47)))

/***/ }),

/***/ 302:
/***/ (function(module, exports) {

exports.read = function (buffer, offset, isLE, mLen, nBytes) {
  var e, m
  var eLen = nBytes * 8 - mLen - 1
  var eMax = (1 << eLen) - 1
  var eBias = eMax >> 1
  var nBits = -7
  var i = isLE ? (nBytes - 1) : 0
  var d = isLE ? -1 : 1
  var s = buffer[offset + i]

  i += d

  e = s & ((1 << (-nBits)) - 1)
  s >>= (-nBits)
  nBits += eLen
  for (; nBits > 0; e = e * 256 + buffer[offset + i], i += d, nBits -= 8) {}

  m = e & ((1 << (-nBits)) - 1)
  e >>= (-nBits)
  nBits += mLen
  for (; nBits > 0; m = m * 256 + buffer[offset + i], i += d, nBits -= 8) {}

  if (e === 0) {
    e = 1 - eBias
  } else if (e === eMax) {
    return m ? NaN : ((s ? -1 : 1) * Infinity)
  } else {
    m = m + Math.pow(2, mLen)
    e = e - eBias
  }
  return (s ? -1 : 1) * m * Math.pow(2, e - mLen)
}

exports.write = function (buffer, value, offset, isLE, mLen, nBytes) {
  var e, m, c
  var eLen = nBytes * 8 - mLen - 1
  var eMax = (1 << eLen) - 1
  var eBias = eMax >> 1
  var rt = (mLen === 23 ? Math.pow(2, -24) - Math.pow(2, -77) : 0)
  var i = isLE ? 0 : (nBytes - 1)
  var d = isLE ? 1 : -1
  var s = value < 0 || (value === 0 && 1 / value < 0) ? 1 : 0

  value = Math.abs(value)

  if (isNaN(value) || value === Infinity) {
    m = isNaN(value) ? 1 : 0
    e = eMax
  } else {
    e = Math.floor(Math.log(value) / Math.LN2)
    if (value * (c = Math.pow(2, -e)) < 1) {
      e--
      c *= 2
    }
    if (e + eBias >= 1) {
      value += rt / c
    } else {
      value += rt * Math.pow(2, 1 - eBias)
    }
    if (value * c >= 2) {
      e++
      c /= 2
    }

    if (e + eBias >= eMax) {
      m = 0
      e = eMax
    } else if (e + eBias >= 1) {
      m = (value * c - 1) * Math.pow(2, mLen)
      e = e + eBias
    } else {
      m = value * Math.pow(2, eBias - 1) * Math.pow(2, mLen)
      e = 0
    }
  }

  for (; mLen >= 8; buffer[offset + i] = m & 0xff, i += d, m /= 256, mLen -= 8) {}

  e = (e << mLen) | m
  eLen += mLen
  for (; eLen > 0; buffer[offset + i] = e & 0xff, i += d, e /= 256, eLen -= 8) {}

  buffer[offset + i - d] |= s * 128
}


/***/ }),

/***/ 303:
/***/ (function(module, exports) {

module.exports = Array.isArray || function (arr) {
  return Object.prototype.toString.call(arr) == '[object Array]';
};


/***/ }),

/***/ 39:
/***/ (function(module, exports, __webpack_require__) {

/* WEBPACK VAR INJECTION */(function(Buffer) {/*
	MIT License http://www.opensource.org/licenses/mit-license.php
	Author Tobias Koppers @sokra
*/
// css base code, injected by the css-loader
module.exports = function(useSourceMap) {
	var list = [];

	// return the list of modules as css string
	list.toString = function toString() {
		return this.map(function (item) {
			var content = cssWithMappingToString(item, useSourceMap);
			if(item[2]) {
				return "@media " + item[2] + "{" + content + "}";
			} else {
				return content;
			}
		}).join("");
	};

	// import a list of modules into the list
	list.i = function(modules, mediaQuery) {
		if(typeof modules === "string")
			modules = [[null, modules, ""]];
		var alreadyImportedModules = {};
		for(var i = 0; i < this.length; i++) {
			var id = this[i][0];
			if(typeof id === "number")
				alreadyImportedModules[id] = true;
		}
		for(i = 0; i < modules.length; i++) {
			var item = modules[i];
			// skip already imported module
			// this implementation is not 100% perfect for weird media query combinations
			//  when a module is imported multiple times with different media queries.
			//  I hope this will never occur (Hey this way we have smaller bundles)
			if(typeof item[0] !== "number" || !alreadyImportedModules[item[0]]) {
				if(mediaQuery && !item[2]) {
					item[2] = mediaQuery;
				} else if(mediaQuery) {
					item[2] = "(" + item[2] + ") and (" + mediaQuery + ")";
				}
				list.push(item);
			}
		}
	};
	return list;
};

function cssWithMappingToString(item, useSourceMap) {
	var content = item[1] || '';
	var cssMapping = item[3];
	if (!cssMapping) {
		return content;
	}

	if (useSourceMap) {
		var sourceMapping = toComment(cssMapping);
		var sourceURLs = cssMapping.sources.map(function (source) {
			return '/*# sourceURL=' + cssMapping.sourceRoot + source + ' */'
		});

		return [content].concat(sourceURLs).concat([sourceMapping]).join('\n');
	}

	return [content].join('\n');
}

// Adapted from convert-source-map (MIT)
function toComment(sourceMap) {
  var base64 = new Buffer(JSON.stringify(sourceMap)).toString('base64');
  var data = 'sourceMappingURL=data:application/json;charset=utf-8;base64,' + base64;

  return '/*# ' + data + ' */';
}

/* WEBPACK VAR INJECTION */}.call(exports, __webpack_require__(282).Buffer))

/***/ }),

/***/ 47:
/***/ (function(module, exports) {

var g;

// This works in non-strict mode
g = (function() {
	return this;
})();

try {
	// This works if eval is allowed (see CSP)
	g = g || Function("return this")() || (1,eval)("this");
} catch(e) {
	// This works if the window reference is available
	if(typeof window === "object")
		g = window;
}

// g can still be undefined, but nothing to do about it...
// We return undefined, instead of nothing here, so it's
// easier to handle this case. if(!global) { ...}

module.exports = g;


/***/ }),

/***/ 562:
/***/ (function(module, exports) {

/*
	MIT License http://www.opensource.org/licenses/mit-license.php
	Author Tobias Koppers @sokra
*/
var stylesInDom = {},
	memoize = function(fn) {
		var memo;
		return function () {
			if (typeof memo === "undefined") memo = fn.apply(this, arguments);
			return memo;
		};
	},
	isOldIE = memoize(function() {
		return /msie [6-9]\b/.test(self.navigator.userAgent.toLowerCase());
	}),
	getHeadElement = memoize(function () {
		return document.head || document.getElementsByTagName("head")[0];
	}),
	singletonElement = null,
	singletonCounter = 0,
	styleElementsInsertedAtTop = [];

module.exports = function(list, options) {
	if(typeof DEBUG !== "undefined" && DEBUG) {
		if(typeof document !== "object") throw new Error("The style-loader cannot be used in a non-browser environment");
	}

	options = options || {};
	// Force single-tag solution on IE6-9, which has a hard limit on the # of <style>
	// tags it will allow on a page
	if (typeof options.singleton === "undefined") options.singleton = isOldIE();

	// By default, add <style> tags to the bottom of <head>.
	if (typeof options.insertAt === "undefined") options.insertAt = "bottom";

	var styles = listToStyles(list);
	addStylesToDom(styles, options);

	return function update(newList) {
		var mayRemove = [];
		for(var i = 0; i < styles.length; i++) {
			var item = styles[i];
			var domStyle = stylesInDom[item.id];
			domStyle.refs--;
			mayRemove.push(domStyle);
		}
		if(newList) {
			var newStyles = listToStyles(newList);
			addStylesToDom(newStyles, options);
		}
		for(var i = 0; i < mayRemove.length; i++) {
			var domStyle = mayRemove[i];
			if(domStyle.refs === 0) {
				for(var j = 0; j < domStyle.parts.length; j++)
					domStyle.parts[j]();
				delete stylesInDom[domStyle.id];
			}
		}
	};
}

function addStylesToDom(styles, options) {
	for(var i = 0; i < styles.length; i++) {
		var item = styles[i];
		var domStyle = stylesInDom[item.id];
		if(domStyle) {
			domStyle.refs++;
			for(var j = 0; j < domStyle.parts.length; j++) {
				domStyle.parts[j](item.parts[j]);
			}
			for(; j < item.parts.length; j++) {
				domStyle.parts.push(addStyle(item.parts[j], options));
			}
		} else {
			var parts = [];
			for(var j = 0; j < item.parts.length; j++) {
				parts.push(addStyle(item.parts[j], options));
			}
			stylesInDom[item.id] = {id: item.id, refs: 1, parts: parts};
		}
	}
}

function listToStyles(list) {
	var styles = [];
	var newStyles = {};
	for(var i = 0; i < list.length; i++) {
		var item = list[i];
		var id = item[0];
		var css = item[1];
		var media = item[2];
		var sourceMap = item[3];
		var part = {css: css, media: media, sourceMap: sourceMap};
		if(!newStyles[id])
			styles.push(newStyles[id] = {id: id, parts: [part]});
		else
			newStyles[id].parts.push(part);
	}
	return styles;
}

function insertStyleElement(options, styleElement) {
	var head = getHeadElement();
	var lastStyleElementInsertedAtTop = styleElementsInsertedAtTop[styleElementsInsertedAtTop.length - 1];
	if (options.insertAt === "top") {
		if(!lastStyleElementInsertedAtTop) {
			head.insertBefore(styleElement, head.firstChild);
		} else if(lastStyleElementInsertedAtTop.nextSibling) {
			head.insertBefore(styleElement, lastStyleElementInsertedAtTop.nextSibling);
		} else {
			head.appendChild(styleElement);
		}
		styleElementsInsertedAtTop.push(styleElement);
	} else if (options.insertAt === "bottom") {
		head.appendChild(styleElement);
	} else {
		throw new Error("Invalid value for parameter 'insertAt'. Must be 'top' or 'bottom'.");
	}
}

function removeStyleElement(styleElement) {
	styleElement.parentNode.removeChild(styleElement);
	var idx = styleElementsInsertedAtTop.indexOf(styleElement);
	if(idx >= 0) {
		styleElementsInsertedAtTop.splice(idx, 1);
	}
}

function createStyleElement(options) {
	var styleElement = document.createElement("style");
	styleElement.type = "text/css";
	insertStyleElement(options, styleElement);
	return styleElement;
}

function createLinkElement(options) {
	var linkElement = document.createElement("link");
	linkElement.rel = "stylesheet";
	insertStyleElement(options, linkElement);
	return linkElement;
}

function addStyle(obj, options) {
	var styleElement, update, remove;

	if (options.singleton) {
		var styleIndex = singletonCounter++;
		styleElement = singletonElement || (singletonElement = createStyleElement(options));
		update = applyToSingletonTag.bind(null, styleElement, styleIndex, false);
		remove = applyToSingletonTag.bind(null, styleElement, styleIndex, true);
	} else if(obj.sourceMap &&
		typeof URL === "function" &&
		typeof URL.createObjectURL === "function" &&
		typeof URL.revokeObjectURL === "function" &&
		typeof Blob === "function" &&
		typeof btoa === "function") {
		styleElement = createLinkElement(options);
		update = updateLink.bind(null, styleElement);
		remove = function() {
			removeStyleElement(styleElement);
			if(styleElement.href)
				URL.revokeObjectURL(styleElement.href);
		};
	} else {
		styleElement = createStyleElement(options);
		update = applyToTag.bind(null, styleElement);
		remove = function() {
			removeStyleElement(styleElement);
		};
	}

	update(obj);

	return function updateStyle(newObj) {
		if(newObj) {
			if(newObj.css === obj.css && newObj.media === obj.media && newObj.sourceMap === obj.sourceMap)
				return;
			update(obj = newObj);
		} else {
			remove();
		}
	};
}

var replaceText = (function () {
	var textStore = [];

	return function (index, replacement) {
		textStore[index] = replacement;
		return textStore.filter(Boolean).join('\n');
	};
})();

function applyToSingletonTag(styleElement, index, remove, obj) {
	var css = remove ? "" : obj.css;

	if (styleElement.styleSheet) {
		styleElement.styleSheet.cssText = replaceText(index, css);
	} else {
		var cssNode = document.createTextNode(css);
		var childNodes = styleElement.childNodes;
		if (childNodes[index]) styleElement.removeChild(childNodes[index]);
		if (childNodes.length) {
			styleElement.insertBefore(cssNode, childNodes[index]);
		} else {
			styleElement.appendChild(cssNode);
		}
	}
}

function applyToTag(styleElement, obj) {
	var css = obj.css;
	var media = obj.media;

	if(media) {
		styleElement.setAttribute("media", media)
	}

	if(styleElement.styleSheet) {
		styleElement.styleSheet.cssText = css;
	} else {
		while(styleElement.firstChild) {
			styleElement.removeChild(styleElement.firstChild);
		}
		styleElement.appendChild(document.createTextNode(css));
	}
}

function updateLink(linkElement, obj) {
	var css = obj.css;
	var sourceMap = obj.sourceMap;

	if(sourceMap) {
		// http://stackoverflow.com/a/26603875
		css += "\n/*# sourceMappingURL=data:application/json;base64," + btoa(unescape(encodeURIComponent(JSON.stringify(sourceMap)))) + " */";
	}

	var blob = new Blob([css], { type: "text/css" });

	var oldSrc = linkElement.href;

	linkElement.href = URL.createObjectURL(blob);

	if(oldSrc)
		URL.revokeObjectURL(oldSrc);
}


/***/ }),

/***/ 565:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(913);
if(typeof content === 'string') content = [[module.i, content, '']];
// add the styles to the DOM
var update = __webpack_require__(562)(content, {});
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../node_modules/css-loader/index.js??ref--10-2!../node_modules/postcss-loader/index.js??postcss!../node_modules/sass-loader/lib/loader.js??ref--10-4!./app-theme.scss", function() {
			var newContent = require("!!../node_modules/css-loader/index.js??ref--10-2!../node_modules/postcss-loader/index.js??postcss!../node_modules/sass-loader/lib/loader.js??ref--10-4!./app-theme.scss");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),

/***/ 566:
/***/ (function(module, exports, __webpack_require__) {

// style-loader: Adds some css to the DOM by adding a <style> tag

// load the styles
var content = __webpack_require__(914);
if(typeof content === 'string') content = [[module.i, content, '']];
// add the styles to the DOM
var update = __webpack_require__(562)(content, {});
if(content.locals) module.exports = content.locals;
// Hot Module Replacement
if(false) {
	// When the styles change, update the <style> tags
	if(!content.locals) {
		module.hot.accept("!!../node_modules/css-loader/index.js??ref--9-2!../node_modules/postcss-loader/index.js??postcss!./styles.css", function() {
			var newContent = require("!!../node_modules/css-loader/index.js??ref--9-2!../node_modules/postcss-loader/index.js??postcss!./styles.css");
			if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
			update(newContent);
		});
	}
	// When the module is disposed, remove the <style> tags
	module.hot.dispose(function() { update(); });
}

/***/ }),

/***/ 913:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(39)(false);
// imports


// module
exports.push([module.i, ".md-elevation-z0 {\n  box-shadow: 0px 0px 0px 0px rgba(0, 0, 0, 0.2), 0px 0px 0px 0px rgba(0, 0, 0, 0.14), 0px 0px 0px 0px rgba(0, 0, 0, 0.12); }\n\n.md-elevation-z1 {\n  box-shadow: 0px 2px 1px -1px rgba(0, 0, 0, 0.2), 0px 1px 1px 0px rgba(0, 0, 0, 0.14), 0px 1px 3px 0px rgba(0, 0, 0, 0.12); }\n\n.md-elevation-z2 {\n  box-shadow: 0px 3px 1px -2px rgba(0, 0, 0, 0.2), 0px 2px 2px 0px rgba(0, 0, 0, 0.14), 0px 1px 5px 0px rgba(0, 0, 0, 0.12); }\n\n.md-elevation-z3 {\n  box-shadow: 0px 3px 3px -2px rgba(0, 0, 0, 0.2), 0px 3px 4px 0px rgba(0, 0, 0, 0.14), 0px 1px 8px 0px rgba(0, 0, 0, 0.12); }\n\n.md-elevation-z4 {\n  box-shadow: 0px 2px 4px -1px rgba(0, 0, 0, 0.2), 0px 4px 5px 0px rgba(0, 0, 0, 0.14), 0px 1px 10px 0px rgba(0, 0, 0, 0.12); }\n\n.md-elevation-z5 {\n  box-shadow: 0px 3px 5px -1px rgba(0, 0, 0, 0.2), 0px 5px 8px 0px rgba(0, 0, 0, 0.14), 0px 1px 14px 0px rgba(0, 0, 0, 0.12); }\n\n.md-elevation-z6 {\n  box-shadow: 0px 3px 5px -1px rgba(0, 0, 0, 0.2), 0px 6px 10px 0px rgba(0, 0, 0, 0.14), 0px 1px 18px 0px rgba(0, 0, 0, 0.12); }\n\n.md-elevation-z7 {\n  box-shadow: 0px 4px 5px -2px rgba(0, 0, 0, 0.2), 0px 7px 10px 1px rgba(0, 0, 0, 0.14), 0px 2px 16px 1px rgba(0, 0, 0, 0.12); }\n\n.md-elevation-z8 {\n  box-shadow: 0px 5px 5px -3px rgba(0, 0, 0, 0.2), 0px 8px 10px 1px rgba(0, 0, 0, 0.14), 0px 3px 14px 2px rgba(0, 0, 0, 0.12); }\n\n.md-elevation-z9 {\n  box-shadow: 0px 5px 6px -3px rgba(0, 0, 0, 0.2), 0px 9px 12px 1px rgba(0, 0, 0, 0.14), 0px 3px 16px 2px rgba(0, 0, 0, 0.12); }\n\n.md-elevation-z10 {\n  box-shadow: 0px 6px 6px -3px rgba(0, 0, 0, 0.2), 0px 10px 14px 1px rgba(0, 0, 0, 0.14), 0px 4px 18px 3px rgba(0, 0, 0, 0.12); }\n\n.md-elevation-z11 {\n  box-shadow: 0px 6px 7px -4px rgba(0, 0, 0, 0.2), 0px 11px 15px 1px rgba(0, 0, 0, 0.14), 0px 4px 20px 3px rgba(0, 0, 0, 0.12); }\n\n.md-elevation-z12 {\n  box-shadow: 0px 7px 8px -4px rgba(0, 0, 0, 0.2), 0px 12px 17px 2px rgba(0, 0, 0, 0.14), 0px 5px 22px 4px rgba(0, 0, 0, 0.12); }\n\n.md-elevation-z13 {\n  box-shadow: 0px 7px 8px -4px rgba(0, 0, 0, 0.2), 0px 13px 19px 2px rgba(0, 0, 0, 0.14), 0px 5px 24px 4px rgba(0, 0, 0, 0.12); }\n\n.md-elevation-z14 {\n  box-shadow: 0px 7px 9px -4px rgba(0, 0, 0, 0.2), 0px 14px 21px 2px rgba(0, 0, 0, 0.14), 0px 5px 26px 4px rgba(0, 0, 0, 0.12); }\n\n.md-elevation-z15 {\n  box-shadow: 0px 8px 9px -5px rgba(0, 0, 0, 0.2), 0px 15px 22px 2px rgba(0, 0, 0, 0.14), 0px 6px 28px 5px rgba(0, 0, 0, 0.12); }\n\n.md-elevation-z16 {\n  box-shadow: 0px 8px 10px -5px rgba(0, 0, 0, 0.2), 0px 16px 24px 2px rgba(0, 0, 0, 0.14), 0px 6px 30px 5px rgba(0, 0, 0, 0.12); }\n\n.md-elevation-z17 {\n  box-shadow: 0px 8px 11px -5px rgba(0, 0, 0, 0.2), 0px 17px 26px 2px rgba(0, 0, 0, 0.14), 0px 6px 32px 5px rgba(0, 0, 0, 0.12); }\n\n.md-elevation-z18 {\n  box-shadow: 0px 9px 11px -5px rgba(0, 0, 0, 0.2), 0px 18px 28px 2px rgba(0, 0, 0, 0.14), 0px 7px 34px 6px rgba(0, 0, 0, 0.12); }\n\n.md-elevation-z19 {\n  box-shadow: 0px 9px 12px -6px rgba(0, 0, 0, 0.2), 0px 19px 29px 2px rgba(0, 0, 0, 0.14), 0px 7px 36px 6px rgba(0, 0, 0, 0.12); }\n\n.md-elevation-z20 {\n  box-shadow: 0px 10px 13px -6px rgba(0, 0, 0, 0.2), 0px 20px 31px 3px rgba(0, 0, 0, 0.14), 0px 8px 38px 7px rgba(0, 0, 0, 0.12); }\n\n.md-elevation-z21 {\n  box-shadow: 0px 10px 13px -6px rgba(0, 0, 0, 0.2), 0px 21px 33px 3px rgba(0, 0, 0, 0.14), 0px 8px 40px 7px rgba(0, 0, 0, 0.12); }\n\n.md-elevation-z22 {\n  box-shadow: 0px 10px 14px -6px rgba(0, 0, 0, 0.2), 0px 22px 35px 3px rgba(0, 0, 0, 0.14), 0px 8px 42px 7px rgba(0, 0, 0, 0.12); }\n\n.md-elevation-z23 {\n  box-shadow: 0px 11px 14px -7px rgba(0, 0, 0, 0.2), 0px 23px 36px 3px rgba(0, 0, 0, 0.14), 0px 9px 44px 8px rgba(0, 0, 0, 0.12); }\n\n.md-elevation-z24 {\n  box-shadow: 0px 11px 15px -7px rgba(0, 0, 0, 0.2), 0px 24px 38px 3px rgba(0, 0, 0, 0.14), 0px 9px 46px 8px rgba(0, 0, 0, 0.12); }\n\n[md-ripple] {\n  overflow: hidden; }\n\n[md-ripple].md-ripple-unbounded {\n  overflow: visible; }\n\n.md-ripple-background {\n  background-color: rgba(0, 0, 0, 0.0588);\n  opacity: 0;\n  transition: opacity 300ms linear;\n  position: absolute;\n  left: 0;\n  top: 0;\n  right: 0;\n  bottom: 0; }\n\n.md-ripple-unbounded .md-ripple-background {\n  display: none; }\n\n.md-ripple-background.md-ripple-active {\n  opacity: 1; }\n\n.md-ripple-focused .md-ripple-background {\n  opacity: 1; }\n\n.md-ripple-foreground {\n  background-color: rgba(0, 0, 0, 0.0588);\n  border-radius: 50%;\n  pointer-events: none;\n  opacity: 0.25;\n  position: absolute;\n  transition: opacity, -webkit-transform 0ms cubic-bezier(0, 0, 0.2, 1);\n  transition: opacity, transform 0ms cubic-bezier(0, 0, 0.2, 1);\n  transition: opacity, transform 0ms cubic-bezier(0, 0, 0.2, 1), -webkit-transform 0ms cubic-bezier(0, 0, 0.2, 1); }\n\n.md-ripple-foreground.md-ripple-fade-in {\n  opacity: 1; }\n\n.md-ripple-foreground.md-ripple-fade-out {\n  opacity: 0; }\n\n.md-visually-hidden {\n  border: 0;\n  clip: rect(0 0 0 0);\n  height: 1px;\n  margin: -1px;\n  overflow: hidden;\n  padding: 0;\n  position: absolute;\n  text-transform: none;\n  width: 1px; }\n\n.md-overlay-container {\n  position: fixed;\n  pointer-events: none;\n  top: 0;\n  left: 0;\n  height: 100%;\n  width: 100%;\n  z-index: 1000; }\n\n.md-overlay-pane {\n  position: absolute;\n  pointer-events: auto;\n  box-sizing: border-box;\n  z-index: 1000; }\n\n.md-overlay-backdrop {\n  position: absolute;\n  top: 0;\n  bottom: 0;\n  left: 0;\n  right: 0;\n  z-index: 1;\n  pointer-events: auto;\n  transition: opacity 400ms cubic-bezier(0.25, 0.8, 0.25, 1);\n  opacity: 0; }\n\n.md-overlay-backdrop.md-overlay-backdrop-showing {\n  opacity: 0.48; }\n\n.md-overlay-dark-backdrop {\n  background: #212121; }\n\n.md-overlay-transparent-backdrop {\n  background: none; }\n\n.md-ripple-focused .md-ripple-background {\n  background-color: rgba(255, 215, 64, 0.1); }\n\n[md-button].md-button-focus.md-primary::after, [md-icon-button].md-button-focus.md-primary::after, [md-raised-button].md-button-focus.md-primary::after, [md-fab].md-button-focus.md-primary::after, [md-mini-fab].md-button-focus.md-primary::after {\n  background-color: rgba(103, 58, 183, 0.12); }\n\n[md-button].md-button-focus.md-accent::after, [md-icon-button].md-button-focus.md-accent::after, [md-raised-button].md-button-focus.md-accent::after, [md-fab].md-button-focus.md-accent::after, [md-mini-fab].md-button-focus.md-accent::after {\n  background-color: rgba(255, 215, 64, 0.12); }\n\n[md-button].md-button-focus.md-warn::after, [md-icon-button].md-button-focus.md-warn::after, [md-raised-button].md-button-focus.md-warn::after, [md-fab].md-button-focus.md-warn::after, [md-mini-fab].md-button-focus.md-warn::after {\n  background-color: rgba(244, 67, 54, 0.12); }\n\n[md-button], [md-icon-button] {\n  background: transparent; }\n  [md-button].md-primary, [md-icon-button].md-primary {\n    color: #673ab7; }\n  [md-button].md-accent, [md-icon-button].md-accent {\n    color: #ffd740; }\n  [md-button].md-warn, [md-icon-button].md-warn {\n    color: #f44336; }\n  [md-button].md-primary[disabled], [md-button].md-accent[disabled], [md-button].md-warn[disabled], [md-button][disabled][disabled], [md-icon-button].md-primary[disabled], [md-icon-button].md-accent[disabled], [md-icon-button].md-warn[disabled], [md-icon-button][disabled][disabled] {\n    color: rgba(0, 0, 0, 0.38); }\n  [md-button]:hover.md-primary::after, [md-icon-button]:hover.md-primary::after {\n    background-color: rgba(103, 58, 183, 0.12); }\n  [md-button]:hover.md-accent::after, [md-icon-button]:hover.md-accent::after {\n    background-color: rgba(255, 215, 64, 0.12); }\n  [md-button]:hover.md-warn::after, [md-icon-button]:hover.md-warn::after {\n    background-color: rgba(244, 67, 54, 0.12); }\n\n[md-raised-button], [md-fab], [md-mini-fab] {\n  background-color: #fafafa; }\n  [md-raised-button].md-primary, [md-fab].md-primary, [md-mini-fab].md-primary {\n    color: rgba(255, 255, 255, 0.87); }\n  [md-raised-button].md-accent, [md-fab].md-accent, [md-mini-fab].md-accent {\n    color: rgba(0, 0, 0, 0.87); }\n  [md-raised-button].md-warn, [md-fab].md-warn, [md-mini-fab].md-warn {\n    color: white; }\n  [md-raised-button].md-primary[disabled], [md-raised-button].md-accent[disabled], [md-raised-button].md-warn[disabled], [md-raised-button][disabled][disabled], [md-fab].md-primary[disabled], [md-fab].md-accent[disabled], [md-fab].md-warn[disabled], [md-fab][disabled][disabled], [md-mini-fab].md-primary[disabled], [md-mini-fab].md-accent[disabled], [md-mini-fab].md-warn[disabled], [md-mini-fab][disabled][disabled] {\n    color: rgba(0, 0, 0, 0.38); }\n  [md-raised-button].md-primary, [md-fab].md-primary, [md-mini-fab].md-primary {\n    background-color: #673ab7; }\n  [md-raised-button].md-accent, [md-fab].md-accent, [md-mini-fab].md-accent {\n    background-color: #ffd740; }\n  [md-raised-button].md-warn, [md-fab].md-warn, [md-mini-fab].md-warn {\n    background-color: #f44336; }\n  [md-raised-button].md-primary[disabled], [md-raised-button].md-accent[disabled], [md-raised-button].md-warn[disabled], [md-raised-button][disabled][disabled], [md-fab].md-primary[disabled], [md-fab].md-accent[disabled], [md-fab].md-warn[disabled], [md-fab][disabled][disabled], [md-mini-fab].md-primary[disabled], [md-mini-fab].md-accent[disabled], [md-mini-fab].md-warn[disabled], [md-mini-fab][disabled][disabled] {\n    background-color: rgba(0, 0, 0, 0.12); }\n\n[md-fab], [md-mini-fab] {\n  background-color: #ffd740;\n  color: rgba(0, 0, 0, 0.87); }\n\n.md-button-toggle-checked .md-button-toggle-label-content {\n  background-color: #e0e0e0; }\n\n.md-button-toggle-disabled .md-button-toggle-label-content {\n  background-color: rgba(0, 0, 0, 0.38); }\n\nmd-card {\n  background: white;\n  color: black; }\n\nmd-card-subtitle {\n  color: rgba(0, 0, 0, 0.54); }\n\n.md-checkbox-frame {\n  border-color: rgba(0, 0, 0, 0.54); }\n\n.md-checkbox-checkmark {\n  fill: #fafafa; }\n\n.md-checkbox-checkmark-path {\n  stroke: #fafafa !important; }\n\n.md-checkbox-mixedmark {\n  background-color: #fafafa; }\n\n.md-checkbox-indeterminate.md-primary .md-checkbox-background, .md-checkbox-checked.md-primary .md-checkbox-background {\n  background-color: #673ab7; }\n\n.md-checkbox-indeterminate.md-accent .md-checkbox-background, .md-checkbox-checked.md-accent .md-checkbox-background {\n  background-color: #ffc107; }\n\n.md-checkbox-indeterminate.md-warn .md-checkbox-background, .md-checkbox-checked.md-warn .md-checkbox-background {\n  background-color: #f44336; }\n\n.md-checkbox-disabled.md-checkbox-checked .md-checkbox-background, .md-checkbox-disabled.md-checkbox-indeterminate .md-checkbox-background {\n  background-color: #b0b0b0; }\n\n.md-checkbox-disabled:not(.md-checkbox-checked) .md-checkbox-frame {\n  border-color: #b0b0b0; }\n\n.md-checkbox:not(.md-checkbox-disabled).md-primary .md-checkbox-ripple .md-ripple-foreground {\n  background-color: rgba(103, 58, 183, 0.26); }\n\n.md-checkbox:not(.md-checkbox-disabled).md-accent .md-checkbox-ripple .md-ripple-foreground {\n  background-color: rgba(255, 215, 64, 0.26); }\n\n.md-checkbox:not(.md-checkbox-disabled).md-warn .md-checkbox-ripple .md-ripple-foreground {\n  background-color: rgba(244, 67, 54, 0.26); }\n\nmd-dialog-container {\n  background: white; }\n\n.md-input-placeholder {\n  color: rgba(0, 0, 0, 0.38); }\n  .md-input-placeholder.md-focused {\n    color: #673ab7; }\n    .md-input-placeholder.md-focused.md-accent {\n      color: #ffd740; }\n    .md-input-placeholder.md-focused.md-warn {\n      color: #f44336; }\n\nmd-input input:-webkit-autofill + .md-input-placeholder .md-placeholder-required, .md-input-placeholder.md-float.md-focused .md-placeholder-required {\n  color: #ffd740; }\n\n.md-input-underline {\n  border-color: rgba(0, 0, 0, 0.38); }\n  .md-input-underline .md-input-ripple {\n    background-color: #673ab7; }\n    .md-input-underline .md-input-ripple.md-accent {\n      background-color: #ffd740; }\n    .md-input-underline .md-input-ripple.md-warn {\n      background-color: #f44336; }\n\nmd-list md-list-item, md-list a[md-list-item], md-nav-list md-list-item, md-nav-list a[md-list-item] {\n  color: black; }\n\nmd-list [md-subheader], md-nav-list [md-subheader] {\n  color: rgba(0, 0, 0, 0.54); }\n\nmd-divider {\n  border-top-color: rgba(0, 0, 0, 0.12); }\n\nmd-nav-list .md-list-item:hover, md-nav-list .md-list-item.md-list-item-focus {\n  background: rgba(0, 0, 0, 0.04); }\n\n.md-menu-content {\n  background: white; }\n\n[md-menu-item] {\n  background: transparent;\n  color: rgba(0, 0, 0, 0.87); }\n  [md-menu-item][disabled] {\n    color: rgba(0, 0, 0, 0.38); }\n  [md-menu-item] md-icon {\n    color: rgba(0, 0, 0, 0.54); }\n  [md-menu-item]:hover:not([disabled]), [md-menu-item]:focus:not([disabled]) {\n    background: rgba(0, 0, 0, 0.04); }\n\n.md-progress-bar-background {\n  background: url(\"data:image/svg+xml;charset=UTF-8,%3Csvg%20version%3D%271.1%27%20xmlns%3D%27http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%27%20xmlns%3Axlink%3D%27http%3A%2F%2Fwww.w3.org%2F1999%2Fxlink%27%20x%3D%270px%27%20y%3D%270px%27%20enable-background%3D%27new%200%200%205%202%27%20xml%3Aspace%3D%27preserve%27%20viewBox%3D%270%200%205%202%27%20preserveAspectRatio%3D%27none%20slice%27%3E%3Ccircle%20cx%3D%271%27%20cy%3D%271%27%20r%3D%271%27%20fill%3D%27#d1c4e9%27%2F%3E%3C%2Fsvg%3E\"); }\n\n.md-progress-bar-buffer {\n  background-color: #d1c4e9; }\n\nmd-progress-bar[color='accent'] .md-progress-bar-background {\n  background: url(\"data:image/svg+xml;charset=UTF-8,%3Csvg%20version%3D%271.1%27%20xmlns%3D%27http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%27%20xmlns%3Axlink%3D%27http%3A%2F%2Fwww.w3.org%2F1999%2Fxlink%27%20x%3D%270px%27%20y%3D%270px%27%20enable-background%3D%27new%200%200%205%202%27%20xml%3Aspace%3D%27preserve%27%20viewBox%3D%270%200%205%202%27%20preserveAspectRatio%3D%27none%20slice%27%3E%3Ccircle%20cx%3D%271%27%20cy%3D%271%27%20r%3D%271%27%20fill%3D%27#ffecb3%27%2F%3E%3C%2Fsvg%3E\"); }\n\nmd-progress-bar[color='accent'] .md-progress-bar-buffer {\n  background-color: #ffecb3; }\n\nmd-progress-bar[color='accent'] .md-progress-bar-fill::after {\n  background-color: #ffb300; }\n\nmd-progress-bar[color='warn'] .md-progress-bar-background {\n  background: url(\"data:image/svg+xml;charset=UTF-8,%3Csvg%20version%3D%271.1%27%20xmlns%3D%27http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%27%20xmlns%3Axlink%3D%27http%3A%2F%2Fwww.w3.org%2F1999%2Fxlink%27%20x%3D%270px%27%20y%3D%270px%27%20enable-background%3D%27new%200%200%205%202%27%20xml%3Aspace%3D%27preserve%27%20viewBox%3D%270%200%205%202%27%20preserveAspectRatio%3D%27none%20slice%27%3E%3Ccircle%20cx%3D%271%27%20cy%3D%271%27%20r%3D%271%27%20fill%3D%27#ffcdd2%27%2F%3E%3C%2Fsvg%3E\"); }\n\nmd-progress-bar[color='warn'] .md-progress-bar-buffer {\n  background-color: #ffcdd2; }\n\nmd-progress-bar[color='warn'] .md-progress-bar-fill::after {\n  background-color: #e53935; }\n\n.md-progress-bar-fill::after {\n  background-color: #5e35b1; }\n\nmd-progress-circle path, md-spinner path {\n  stroke: #5e35b1; }\n\nmd-progress-circle[color='accent'] path, md-spinner[color='accent'] path {\n  stroke: #ffb300; }\n\nmd-progress-circle[color='warn'] path, md-spinner[color='warn'] path {\n  stroke: #e53935; }\n\n.md-radio-outer-circle {\n  border-color: rgba(0, 0, 0, 0.54); }\n  .md-radio-checked .md-radio-outer-circle {\n    border-color: #ffd740; }\n  .md-radio-disabled .md-radio-outer-circle {\n    border-color: rgba(0, 0, 0, 0.38); }\n\n.md-radio-inner-circle {\n  background-color: #ffd740; }\n  .md-radio-disabled .md-radio-inner-circle {\n    background-color: rgba(0, 0, 0, 0.38); }\n\n.md-radio-ripple .md-ripple-foreground {\n  background-color: rgba(255, 215, 64, 0.26); }\n  .md-radio-disabled .md-radio-ripple .md-ripple-foreground {\n    background-color: rgba(0, 0, 0, 0.38); }\n\nmd-sidenav-layout {\n  background-color: #fafafa;\n  color: rgba(0, 0, 0, 0.87); }\n\nmd-sidenav {\n  background-color: white;\n  color: rgba(0, 0, 0, 0.87); }\n  md-sidenav.md-sidenav-push {\n    background-color: white; }\n\n.md-sidenav-backdrop.md-sidenav-shown {\n  background-color: rgba(0, 0, 0, 0.6); }\n\nmd-slide-toggle.md-checked:not(.md-disabled) .md-slide-toggle-thumb {\n  background-color: #ffc107; }\n\nmd-slide-toggle.md-checked:not(.md-disabled) .md-slide-toggle-bar {\n  background-color: rgba(255, 193, 7, 0.5); }\n\nmd-slide-toggle.md-slide-toggle-focused:not(.md-checked) .md-ink-ripple {\n  background-color: rgba(0, 0, 0, 0.12); }\n\nmd-slide-toggle.md-slide-toggle-focused .md-ink-ripple {\n  background-color: rgba(255, 193, 7, 0.26); }\n\nmd-slide-toggle.md-primary.md-checked:not(.md-disabled) .md-slide-toggle-thumb {\n  background-color: #673ab7; }\n\nmd-slide-toggle.md-primary.md-checked:not(.md-disabled) .md-slide-toggle-bar {\n  background-color: rgba(103, 58, 183, 0.5); }\n\nmd-slide-toggle.md-primary.md-slide-toggle-focused:not(.md-checked) .md-ink-ripple {\n  background-color: rgba(0, 0, 0, 0.12); }\n\nmd-slide-toggle.md-primary.md-slide-toggle-focused .md-ink-ripple {\n  background-color: rgba(103, 58, 183, 0.26); }\n\nmd-slide-toggle.md-warn.md-checked:not(.md-disabled) .md-slide-toggle-thumb {\n  background-color: #f44336; }\n\nmd-slide-toggle.md-warn.md-checked:not(.md-disabled) .md-slide-toggle-bar {\n  background-color: rgba(244, 67, 54, 0.5); }\n\nmd-slide-toggle.md-warn.md-slide-toggle-focused:not(.md-checked) .md-ink-ripple {\n  background-color: rgba(0, 0, 0, 0.12); }\n\nmd-slide-toggle.md-warn.md-slide-toggle-focused .md-ink-ripple {\n  background-color: rgba(244, 67, 54, 0.26); }\n\n.md-disabled .md-slide-toggle-thumb {\n  background-color: #bdbdbd; }\n\n.md-disabled .md-slide-toggle-bar {\n  background-color: rgba(0, 0, 0, 0.1); }\n\n.md-slide-toggle-thumb {\n  background-color: #fafafa; }\n\n.md-slide-toggle-bar {\n  background-color: rgba(0, 0, 0, 0.38); }\n\n.md-slider-track {\n  background-color: rgba(0, 0, 0, 0.26); }\n\n.md-slider-track-fill {\n  background-color: #ffd740; }\n\n.md-slider-thumb {\n  background-color: #ffd740; }\n\n.md-slider-thumb-label {\n  background-color: #ffd740; }\n\n.md-slider-thumb-label-text {\n  color: rgba(0, 0, 0, 0.87); }\n\n[md-tab-nav-bar],\n.md-tab-header {\n  border-bottom: 1px solid #e0e0e0; }\n\n.md-tab-label:focus {\n  background-color: rgba(209, 196, 233, 0.3); }\n\nmd-ink-bar {\n  background-color: #673ab7; }\n\nmd-toolbar {\n  background: whitesmoke;\n  color: rgba(0, 0, 0, 0.87); }\n  md-toolbar.md-primary {\n    background: #673ab7;\n    color: rgba(255, 255, 255, 0.87); }\n  md-toolbar.md-accent {\n    background: #ffd740;\n    color: rgba(0, 0, 0, 0.87); }\n  md-toolbar.md-warn {\n    background: #f44336;\n    color: white; }\n\n.md-tooltip {\n  background: rgba(97, 97, 97, 0.9); }\n\n.m2app-dark .md-ripple-focused .md-ripple-background {\n  background-color: rgba(176, 190, 197, 0.1); }\n\n.m2app-dark [md-button].md-button-focus.md-primary::after, .m2app-dark [md-icon-button].md-button-focus.md-primary::after, .m2app-dark [md-raised-button].md-button-focus.md-primary::after, .m2app-dark [md-fab].md-button-focus.md-primary::after, .m2app-dark [md-mini-fab].md-button-focus.md-primary::after {\n  background-color: rgba(194, 24, 91, 0.12); }\n\n.m2app-dark [md-button].md-button-focus.md-accent::after, .m2app-dark [md-icon-button].md-button-focus.md-accent::after, .m2app-dark [md-raised-button].md-button-focus.md-accent::after, .m2app-dark [md-fab].md-button-focus.md-accent::after, .m2app-dark [md-mini-fab].md-button-focus.md-accent::after {\n  background-color: rgba(176, 190, 197, 0.12); }\n\n.m2app-dark [md-button].md-button-focus.md-warn::after, .m2app-dark [md-icon-button].md-button-focus.md-warn::after, .m2app-dark [md-raised-button].md-button-focus.md-warn::after, .m2app-dark [md-fab].md-button-focus.md-warn::after, .m2app-dark [md-mini-fab].md-button-focus.md-warn::after {\n  background-color: rgba(255, 87, 34, 0.12); }\n\n.m2app-dark [md-button], .m2app-dark [md-icon-button] {\n  background: transparent; }\n  .m2app-dark [md-button].md-primary, .m2app-dark [md-icon-button].md-primary {\n    color: #c2185b; }\n  .m2app-dark [md-button].md-accent, .m2app-dark [md-icon-button].md-accent {\n    color: #b0bec5; }\n  .m2app-dark [md-button].md-warn, .m2app-dark [md-icon-button].md-warn {\n    color: #ff5722; }\n  .m2app-dark [md-button].md-primary[disabled], .m2app-dark [md-button].md-accent[disabled], .m2app-dark [md-button].md-warn[disabled], .m2app-dark [md-button][disabled][disabled], .m2app-dark [md-icon-button].md-primary[disabled], .m2app-dark [md-icon-button].md-accent[disabled], .m2app-dark [md-icon-button].md-warn[disabled], .m2app-dark [md-icon-button][disabled][disabled] {\n    color: rgba(255, 255, 255, 0.3); }\n  .m2app-dark [md-button]:hover.md-primary::after, .m2app-dark [md-icon-button]:hover.md-primary::after {\n    background-color: rgba(194, 24, 91, 0.12); }\n  .m2app-dark [md-button]:hover.md-accent::after, .m2app-dark [md-icon-button]:hover.md-accent::after {\n    background-color: rgba(176, 190, 197, 0.12); }\n  .m2app-dark [md-button]:hover.md-warn::after, .m2app-dark [md-icon-button]:hover.md-warn::after {\n    background-color: rgba(255, 87, 34, 0.12); }\n\n.m2app-dark [md-raised-button], .m2app-dark [md-fab], .m2app-dark [md-mini-fab] {\n  background-color: #303030; }\n  .m2app-dark [md-raised-button].md-primary, .m2app-dark [md-fab].md-primary, .m2app-dark [md-mini-fab].md-primary {\n    color: rgba(255, 255, 255, 0.87); }\n  .m2app-dark [md-raised-button].md-accent, .m2app-dark [md-fab].md-accent, .m2app-dark [md-mini-fab].md-accent {\n    color: rgba(0, 0, 0, 0.87); }\n  .m2app-dark [md-raised-button].md-warn, .m2app-dark [md-fab].md-warn, .m2app-dark [md-mini-fab].md-warn {\n    color: white; }\n  .m2app-dark [md-raised-button].md-primary[disabled], .m2app-dark [md-raised-button].md-accent[disabled], .m2app-dark [md-raised-button].md-warn[disabled], .m2app-dark [md-raised-button][disabled][disabled], .m2app-dark [md-fab].md-primary[disabled], .m2app-dark [md-fab].md-accent[disabled], .m2app-dark [md-fab].md-warn[disabled], .m2app-dark [md-fab][disabled][disabled], .m2app-dark [md-mini-fab].md-primary[disabled], .m2app-dark [md-mini-fab].md-accent[disabled], .m2app-dark [md-mini-fab].md-warn[disabled], .m2app-dark [md-mini-fab][disabled][disabled] {\n    color: rgba(255, 255, 255, 0.3); }\n  .m2app-dark [md-raised-button].md-primary, .m2app-dark [md-fab].md-primary, .m2app-dark [md-mini-fab].md-primary {\n    background-color: #c2185b; }\n  .m2app-dark [md-raised-button].md-accent, .m2app-dark [md-fab].md-accent, .m2app-dark [md-mini-fab].md-accent {\n    background-color: #b0bec5; }\n  .m2app-dark [md-raised-button].md-warn, .m2app-dark [md-fab].md-warn, .m2app-dark [md-mini-fab].md-warn {\n    background-color: #ff5722; }\n  .m2app-dark [md-raised-button].md-primary[disabled], .m2app-dark [md-raised-button].md-accent[disabled], .m2app-dark [md-raised-button].md-warn[disabled], .m2app-dark [md-raised-button][disabled][disabled], .m2app-dark [md-fab].md-primary[disabled], .m2app-dark [md-fab].md-accent[disabled], .m2app-dark [md-fab].md-warn[disabled], .m2app-dark [md-fab][disabled][disabled], .m2app-dark [md-mini-fab].md-primary[disabled], .m2app-dark [md-mini-fab].md-accent[disabled], .m2app-dark [md-mini-fab].md-warn[disabled], .m2app-dark [md-mini-fab][disabled][disabled] {\n    background-color: rgba(255, 255, 255, 0.12); }\n\n.m2app-dark [md-fab], .m2app-dark [md-mini-fab] {\n  background-color: #b0bec5;\n  color: rgba(0, 0, 0, 0.87); }\n\n.m2app-dark .md-button-toggle-checked .md-button-toggle-label-content {\n  background-color: #e0e0e0; }\n\n.m2app-dark .md-button-toggle-disabled .md-button-toggle-label-content {\n  background-color: rgba(255, 255, 255, 0.3); }\n\n.m2app-dark md-card {\n  background: #424242;\n  color: white; }\n\n.m2app-dark md-card-subtitle {\n  color: rgba(255, 255, 255, 0.7); }\n\n.m2app-dark .md-checkbox-frame {\n  border-color: rgba(255, 255, 255, 0.7); }\n\n.m2app-dark .md-checkbox-checkmark {\n  fill: #303030; }\n\n.m2app-dark .md-checkbox-checkmark-path {\n  stroke: #303030 !important; }\n\n.m2app-dark .md-checkbox-mixedmark {\n  background-color: #303030; }\n\n.m2app-dark .md-checkbox-indeterminate.md-primary .md-checkbox-background, .m2app-dark .md-checkbox-checked.md-primary .md-checkbox-background {\n  background-color: #e91e63; }\n\n.m2app-dark .md-checkbox-indeterminate.md-accent .md-checkbox-background, .m2app-dark .md-checkbox-checked.md-accent .md-checkbox-background {\n  background-color: #607d8b; }\n\n.m2app-dark .md-checkbox-indeterminate.md-warn .md-checkbox-background, .m2app-dark .md-checkbox-checked.md-warn .md-checkbox-background {\n  background-color: #ff5722; }\n\n.m2app-dark .md-checkbox-disabled.md-checkbox-checked .md-checkbox-background, .m2app-dark .md-checkbox-disabled.md-checkbox-indeterminate .md-checkbox-background {\n  background-color: #686868; }\n\n.m2app-dark .md-checkbox-disabled:not(.md-checkbox-checked) .md-checkbox-frame {\n  border-color: #686868; }\n\n.m2app-dark .md-checkbox:not(.md-checkbox-disabled).md-primary .md-checkbox-ripple .md-ripple-foreground {\n  background-color: rgba(194, 24, 91, 0.26); }\n\n.m2app-dark .md-checkbox:not(.md-checkbox-disabled).md-accent .md-checkbox-ripple .md-ripple-foreground {\n  background-color: rgba(176, 190, 197, 0.26); }\n\n.m2app-dark .md-checkbox:not(.md-checkbox-disabled).md-warn .md-checkbox-ripple .md-ripple-foreground {\n  background-color: rgba(255, 87, 34, 0.26); }\n\n.m2app-dark md-dialog-container {\n  background: #424242; }\n\n.m2app-dark .md-input-placeholder {\n  color: rgba(255, 255, 255, 0.3); }\n  .m2app-dark .md-input-placeholder.md-focused {\n    color: #c2185b; }\n    .m2app-dark .md-input-placeholder.md-focused.md-accent {\n      color: #b0bec5; }\n    .m2app-dark .md-input-placeholder.md-focused.md-warn {\n      color: #ff5722; }\n\n.m2app-dark md-input input:-webkit-autofill + .md-input-placeholder .md-placeholder-required, .m2app-dark .md-input-placeholder.md-float.md-focused .md-placeholder-required {\n  color: #b0bec5; }\n\n.m2app-dark .md-input-underline {\n  border-color: rgba(255, 255, 255, 0.3); }\n  .m2app-dark .md-input-underline .md-input-ripple {\n    background-color: #c2185b; }\n    .m2app-dark .md-input-underline .md-input-ripple.md-accent {\n      background-color: #b0bec5; }\n    .m2app-dark .md-input-underline .md-input-ripple.md-warn {\n      background-color: #ff5722; }\n\n.m2app-dark md-list md-list-item, .m2app-dark md-list a[md-list-item], .m2app-dark md-nav-list md-list-item, .m2app-dark md-nav-list a[md-list-item] {\n  color: white; }\n\n.m2app-dark md-list [md-subheader], .m2app-dark md-nav-list [md-subheader] {\n  color: rgba(255, 255, 255, 0.7); }\n\n.m2app-dark md-divider {\n  border-top-color: rgba(255, 255, 255, 0.12); }\n\n.m2app-dark md-nav-list .md-list-item:hover, .m2app-dark md-nav-list .md-list-item.md-list-item-focus {\n  background: rgba(255, 255, 255, 0.04); }\n\n.m2app-dark .md-menu-content {\n  background: #424242; }\n\n.m2app-dark [md-menu-item] {\n  background: transparent;\n  color: white; }\n  .m2app-dark [md-menu-item][disabled] {\n    color: rgba(255, 255, 255, 0.3); }\n  .m2app-dark [md-menu-item] md-icon {\n    color: white; }\n  .m2app-dark [md-menu-item]:hover:not([disabled]), .m2app-dark [md-menu-item]:focus:not([disabled]) {\n    background: rgba(255, 255, 255, 0.04); }\n\n.m2app-dark .md-progress-bar-background {\n  background: url(\"data:image/svg+xml;charset=UTF-8,%3Csvg%20version%3D%271.1%27%20xmlns%3D%27http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%27%20xmlns%3Axlink%3D%27http%3A%2F%2Fwww.w3.org%2F1999%2Fxlink%27%20x%3D%270px%27%20y%3D%270px%27%20enable-background%3D%27new%200%200%205%202%27%20xml%3Aspace%3D%27preserve%27%20viewBox%3D%270%200%205%202%27%20preserveAspectRatio%3D%27none%20slice%27%3E%3Ccircle%20cx%3D%271%27%20cy%3D%271%27%20r%3D%271%27%20fill%3D%27#f8bbd0%27%2F%3E%3C%2Fsvg%3E\"); }\n\n.m2app-dark .md-progress-bar-buffer {\n  background-color: #f8bbd0; }\n\n.m2app-dark md-progress-bar[color='accent'] .md-progress-bar-background {\n  background: url(\"data:image/svg+xml;charset=UTF-8,%3Csvg%20version%3D%271.1%27%20xmlns%3D%27http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%27%20xmlns%3Axlink%3D%27http%3A%2F%2Fwww.w3.org%2F1999%2Fxlink%27%20x%3D%270px%27%20y%3D%270px%27%20enable-background%3D%27new%200%200%205%202%27%20xml%3Aspace%3D%27preserve%27%20viewBox%3D%270%200%205%202%27%20preserveAspectRatio%3D%27none%20slice%27%3E%3Ccircle%20cx%3D%271%27%20cy%3D%271%27%20r%3D%271%27%20fill%3D%27#cfd8dc%27%2F%3E%3C%2Fsvg%3E\"); }\n\n.m2app-dark md-progress-bar[color='accent'] .md-progress-bar-buffer {\n  background-color: #cfd8dc; }\n\n.m2app-dark md-progress-bar[color='accent'] .md-progress-bar-fill::after {\n  background-color: #546e7a; }\n\n.m2app-dark md-progress-bar[color='warn'] .md-progress-bar-background {\n  background: url(\"data:image/svg+xml;charset=UTF-8,%3Csvg%20version%3D%271.1%27%20xmlns%3D%27http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%27%20xmlns%3Axlink%3D%27http%3A%2F%2Fwww.w3.org%2F1999%2Fxlink%27%20x%3D%270px%27%20y%3D%270px%27%20enable-background%3D%27new%200%200%205%202%27%20xml%3Aspace%3D%27preserve%27%20viewBox%3D%270%200%205%202%27%20preserveAspectRatio%3D%27none%20slice%27%3E%3Ccircle%20cx%3D%271%27%20cy%3D%271%27%20r%3D%271%27%20fill%3D%27#ffccbc%27%2F%3E%3C%2Fsvg%3E\"); }\n\n.m2app-dark md-progress-bar[color='warn'] .md-progress-bar-buffer {\n  background-color: #ffccbc; }\n\n.m2app-dark md-progress-bar[color='warn'] .md-progress-bar-fill::after {\n  background-color: #f4511e; }\n\n.m2app-dark .md-progress-bar-fill::after {\n  background-color: #d81b60; }\n\n.m2app-dark md-progress-circle path, .m2app-dark md-spinner path {\n  stroke: #d81b60; }\n\n.m2app-dark md-progress-circle[color='accent'] path, .m2app-dark md-spinner[color='accent'] path {\n  stroke: #546e7a; }\n\n.m2app-dark md-progress-circle[color='warn'] path, .m2app-dark md-spinner[color='warn'] path {\n  stroke: #f4511e; }\n\n.m2app-dark .md-radio-outer-circle {\n  border-color: rgba(255, 255, 255, 0.7); }\n  .md-radio-checked .m2app-dark .md-radio-outer-circle {\n    border-color: #b0bec5; }\n  .md-radio-disabled .m2app-dark .md-radio-outer-circle {\n    border-color: rgba(255, 255, 255, 0.3); }\n\n.m2app-dark .md-radio-inner-circle {\n  background-color: #b0bec5; }\n  .md-radio-disabled .m2app-dark .md-radio-inner-circle {\n    background-color: rgba(255, 255, 255, 0.3); }\n\n.m2app-dark .md-radio-ripple .md-ripple-foreground {\n  background-color: rgba(176, 190, 197, 0.26); }\n  .md-radio-disabled .m2app-dark .md-radio-ripple .md-ripple-foreground {\n    background-color: rgba(255, 255, 255, 0.3); }\n\n.m2app-dark md-sidenav-layout {\n  background-color: #303030;\n  color: white; }\n\n.m2app-dark md-sidenav {\n  background-color: #424242;\n  color: white; }\n  .m2app-dark md-sidenav.md-sidenav-push {\n    background-color: #424242; }\n\n.m2app-dark .md-sidenav-backdrop.md-sidenav-shown {\n  background-color: rgba(189, 189, 189, 0.6); }\n\n.m2app-dark md-slide-toggle.md-checked:not(.md-disabled) .md-slide-toggle-thumb {\n  background-color: #b0bec5; }\n\n.m2app-dark md-slide-toggle.md-checked:not(.md-disabled) .md-slide-toggle-bar {\n  background-color: rgba(176, 190, 197, 0.5); }\n\n.m2app-dark md-slide-toggle.md-slide-toggle-focused:not(.md-checked) .md-ink-ripple {\n  background-color: rgba(255, 255, 255, 0.12); }\n\n.m2app-dark md-slide-toggle.md-slide-toggle-focused .md-ink-ripple {\n  background-color: rgba(176, 190, 197, 0.26); }\n\n.m2app-dark md-slide-toggle.md-primary.md-checked:not(.md-disabled) .md-slide-toggle-thumb {\n  background-color: #f48fb1; }\n\n.m2app-dark md-slide-toggle.md-primary.md-checked:not(.md-disabled) .md-slide-toggle-bar {\n  background-color: rgba(244, 143, 177, 0.5); }\n\n.m2app-dark md-slide-toggle.md-primary.md-slide-toggle-focused:not(.md-checked) .md-ink-ripple {\n  background-color: rgba(255, 255, 255, 0.12); }\n\n.m2app-dark md-slide-toggle.md-primary.md-slide-toggle-focused .md-ink-ripple {\n  background-color: rgba(244, 143, 177, 0.26); }\n\n.m2app-dark md-slide-toggle.md-warn.md-checked:not(.md-disabled) .md-slide-toggle-thumb {\n  background-color: #ffab91; }\n\n.m2app-dark md-slide-toggle.md-warn.md-checked:not(.md-disabled) .md-slide-toggle-bar {\n  background-color: rgba(255, 171, 145, 0.5); }\n\n.m2app-dark md-slide-toggle.md-warn.md-slide-toggle-focused:not(.md-checked) .md-ink-ripple {\n  background-color: rgba(255, 255, 255, 0.12); }\n\n.m2app-dark md-slide-toggle.md-warn.md-slide-toggle-focused .md-ink-ripple {\n  background-color: rgba(255, 171, 145, 0.26); }\n\n.m2app-dark .md-disabled .md-slide-toggle-thumb {\n  background-color: #424242; }\n\n.m2app-dark .md-disabled .md-slide-toggle-bar {\n  background-color: rgba(255, 255, 255, 0.12); }\n\n.m2app-dark .md-slide-toggle-thumb {\n  background-color: #bdbdbd; }\n\n.m2app-dark .md-slide-toggle-bar {\n  background-color: rgba(255, 255, 255, 0.3); }\n\n.m2app-dark .md-slider-track {\n  background-color: rgba(0, 0, 0, 0.26); }\n\n.m2app-dark .md-slider-track-fill {\n  background-color: #b0bec5; }\n\n.m2app-dark .md-slider-thumb {\n  background-color: #b0bec5; }\n\n.m2app-dark .md-slider-thumb-label {\n  background-color: #b0bec5; }\n\n.m2app-dark .md-slider-thumb-label-text {\n  color: rgba(0, 0, 0, 0.87); }\n\n.m2app-dark [md-tab-nav-bar],\n.m2app-dark .md-tab-header {\n  border-bottom: 1px solid black; }\n\n.m2app-dark .md-tab-label:focus {\n  background-color: rgba(248, 187, 208, 0.3); }\n\n.m2app-dark md-ink-bar {\n  background-color: #e91e63; }\n\n.m2app-dark md-toolbar {\n  background: #212121;\n  color: white; }\n  .m2app-dark md-toolbar.md-primary {\n    background: #c2185b;\n    color: rgba(255, 255, 255, 0.87); }\n  .m2app-dark md-toolbar.md-accent {\n    background: #b0bec5;\n    color: rgba(0, 0, 0, 0.87); }\n  .m2app-dark md-toolbar.md-warn {\n    background: #ff5722;\n    color: white; }\n\n.m2app-dark .md-tooltip {\n  background: rgba(97, 97, 97, 0.9); }\n", ""]);

// exports


/***/ }),

/***/ 914:
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__(39)(false);
// imports


// module
exports.push([module.i, "\r\nbody {\r\n\tmargin:0;\r\n\t/* background: url(/app/resources/images/background.jpg) no-repeat center center fixed; \r\n  -webkit-background-size: cover;\r\n  -moz-background-size: cover;\r\n  -o-background-size: cover;\r\n  background-size: cover;*/\r\n}", ""]);

// exports


/***/ })

},[1265]);
//# sourceMappingURL=styles.bundle.js.map